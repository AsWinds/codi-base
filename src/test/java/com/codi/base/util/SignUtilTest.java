package com.codi.base.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.TreeMap;

import static com.codi.base.util.SignUtil.getSalt;
import static com.codi.base.util.SignUtil.getSign;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-06-28 15:48
 * @since 1.0
 */
@Slf4j
public class SignUtilTest {
    @Test
    public void run() {
        TreeMap<String, Object> sortMap = new TreeMap<>();

        sortMap.put("token", "1");
        sortMap.put("page", "1");
        sortMap.put("size", "1");

        String requestStr = SignUtil.getRequestString(sortMap);

        log.debug("requestStr={}", requestStr);
        log.debug("salt byte={}", getSalt("1"));
        log.debug("sign={}", getSign(requestStr, getSalt("1")));
        log.debug("sign={}", getSign(requestStr, getSalt("72f62e999ed1500ac6966386a2aeaca4")));

    }
}
