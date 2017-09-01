package com.codi.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-06-22 11:26
 */
@Slf4j
public class ExceptionHandlerTest {

    @Test
    public void run() throws BaseAppException {

        ExceptionHandler.publish("asdfasdf");
    }

}
