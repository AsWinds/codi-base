package com.codi.base.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务基类
 *
 * @author shi.pengyan
 * @date 2016年11月23日 上午10:38:34
 */
public abstract class BaseServiceImpl {
    /* logger */
    protected Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    protected RedisTemplate<String, String> redisTemplate;
}
