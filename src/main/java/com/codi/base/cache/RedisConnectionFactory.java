package com.codi.base.cache;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import com.codi.base.config.ConfigurationMgr;
import com.codi.base.util.RSAUtil;
import com.codi.base.util.StringUtil;

/**
 * Redis连接工厂
 *
 * @author shi.pengyan
 * @date 2016年10月5日 下午4:41:26
 */
public class RedisConnectionFactory extends JedisConnectionFactory {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectionFactory.class);

    private final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJrnsGEkWjTdfPGUTlSa7rt24RaWNXZLB8c8eDVyHJRNSU82G+3BwiRpcsZsViX/Ow/McgD0AZfG0kp3nIRtM4+w3MlKsVZhH9mm9OogwoDIAHlYe7zG7uiY5qEjlUwJgs4QdU7Oy2xYDlejlXfEMHXu3IDw7etDWHtR2Bp2R8Z/AgMBAAECgYAPkIIXcKijsTGRsH4iYCLmsS4yr8sVZLqWWUwWyq12dUGNdPlfbV2NfQ7sQwxw6vFLSBaYY+wZDQAqPVR7teE9QLnSZe5Su+RIki8/Wj9jSV/eDEnbU6ZbON9QTn2wl4wuOrmO8hwftapR1XpjCxdwHjHIkZ7MQ/rL8Wfn93Fy6QJBAPxZJqi7tqIAGv/aXB5Fa2kBaAp2oXw6m+2Kn6GuaJUP+tXyRjCBl13BBvtAWf05BvBy1oz74/nomJufbUpLt5UCQQCdJYzLN83sVh1WSX8P30OaqlcA+JCF2O0GYv8kwSziArb4Hpcc0+1mdF3ERqJTmh9ZxpbH9a/wy/yEix5TszDDAkBtP9eJ04LVDgCiZhHdlSZUVqSJaySsmN8q7wn/QHfMmRd6iqIym9hBYbE5E3oiNA86CC206dSgPD/dmB8DvFYlAkEAl/vxJ7pZFvJSfVmn4hn0sGl+rBm67TjYRQ0J5upOFmi5vU8Yp3Dwcd6psWL/LAwrthAzVEFyYFlpaGeEdT8hMQJACGhHiiv4CuVHVkRg3NpDlvlV3px3ysKqHOHyd21Duyqdx107bkdQPKLJJj14fD//D9siN9eocIaBg4nw5b3YBg==";

    public RedisConnectionFactory() {
        ConfigurationMgr configMgr = ConfigurationMgr.getInstance();

        String host = configMgr.getString("redis.host");
        int port = configMgr.getInt("redis.port", 6379);
        String password = configMgr.getString("redis.password");
        boolean encrypt = configMgr.getBoolean("redis.encrypt", false);
        String publicKeyStr = configMgr.getString("redis.publicKey");
        int maxIdle = configMgr.getInt("redis.maxIdle", 300);
        int maxTotal = configMgr.getInt("redis.maxTotal", 600);
        long maxWaitMillis = configMgr.getLong("redis.maxWaitMillis", 1000);
        boolean testOnBorrow = configMgr.getBoolean("redis.testOnBorrow", true);
        int dbIndex = configMgr.getInt("redis.dataBase", 0); // 默认选择0数据库

        logger.debug("host={},port={}", host, port);
        logger.debug("encrypt={},password={}", encrypt, password);
        logger.debug("publicKey={}", publicKeyStr);
        logger.debug("maxIdle={},maxTotal={}", maxIdle, maxTotal);
        logger.debug("maxWaitMillis={},testOnBorrow={}", maxWaitMillis, testOnBorrow);

        setHostName(host);
        setPort(port);
        setUsePool(true);
        setDatabase(dbIndex);

        if (encrypt) {
            try {
                byte[] buffer = RSAUtil.decrypt(RSAUtil.loadPrivateKeyByStr(privateKey), Base64.decodeBase64(password));
                String pwd = new String(buffer);
                setPassword(pwd);
            } catch (Exception e) {
                logger.error("error", e);
            }

        } else {
            if (StringUtil.isNotEmpty(password)) {
                setPassword(password);
            }
        }

        // 设置jedis连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        // testOnBorrow：在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
        poolConfig.setTestOnBorrow(testOnBorrow);

        setPoolConfig(poolConfig);
    }

    /**
     * 暴露Jedis对象, 目前只使用在redis分布式锁发送短信，用于给每个线程一个自己的Jedis
     * Returns a Jedis instance to be used as a Redis connection. The instance can be newly created or retrieved from a
     * pool.
     *
     * @return Jedis instance ready for wrapping into a {@link RedisConnection}.
     */
    public Jedis fetchJedisConnector() {
        return super.fetchJedisConnector();
    }
}
