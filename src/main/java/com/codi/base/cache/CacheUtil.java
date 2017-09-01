package com.codi.base.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * key规则策略& 常用方法
 *
 * @author shi.pengyan
 * @date 2016年10月8日 上午10:11:39
 */
public class CacheUtil {

    private CacheUtil() {
    }

    /**
     * 获取cacheKey主键
     *
     * @param nameSpace
     *            前缀
     * @param keys
     *            keys
     * @return
     */
    public static String getKey(String nameSpace, String... keys) {
        StringBuilder sb = new StringBuilder(nameSpace);

        if (keys != null) {
            for (String key : keys) {
                sb.append(":").append(key);
            }
        }

        return sb.toString();
    }

    /**
     *
     * @param nameSpace
     * @param keys
     * @return
     */
    public static String getKey(String nameSpace, Object... keys) {
        StringBuilder sb = new StringBuilder(nameSpace);

        if (keys != null) {
            for (Object key : keys) {
                if (key instanceof Integer) {
                    sb.append(":").append(((Integer) key).intValue());
                } else {
                    sb.append(":").append(key);
                }
            }
        }

        return sb.toString();
    }

    public static String getStr(RedisTemplate<String, String> redisTemplate, String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static void setStr(RedisTemplate<String, String> redisTemplate, String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static <T> T getStrObj(RedisTemplate<String, String> redisTemplate, String key, Class<T> clazz) {
        String text = redisTemplate.opsForValue().get(key);
        return JSON.parseObject(text, clazz);
    }

    public static void setStrObj(RedisTemplate<String, String> redisTemplate, String key, Object object) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(object));
    }

    /**
     * 设置kv过期时间
     *
     * @param redisTemplate
     * @param key
     * @param object
     * @param timeout
     * @param unit
     */
    public static void setStrObj(RedisTemplate<String, String> redisTemplate, String key, Object object, long timeout,
            TimeUnit unit) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(object), timeout, unit);
    }

    public static <T> List<T> getStrList(RedisTemplate<String, String> redisTemplate, String key, Class<T> clazz) {
        String text = redisTemplate.opsForValue().get(key);
        return JSON.parseArray(text, clazz);
    }

    public static void setStrList(RedisTemplate<String, String> redisTemplate, String key, Object object) {
        setStrObj(redisTemplate, key, object);
    }

}
