package com.codi.base.spring;

import com.codi.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-06-22 14:19
 */
@Slf4j
public class MessageContextTest extends BaseSpringTest {

    @Test
    public void run() {
        readResSimple("user.code.invalid");
        readResSimple("user.code.repeat.tip", "spy", "shipengyan");
    }


    private void readResSimple(String key, Object... args) {
        String msg = MessageContext.get(key, args);
        log.debug("{}={}", key, msg);
    }
}
