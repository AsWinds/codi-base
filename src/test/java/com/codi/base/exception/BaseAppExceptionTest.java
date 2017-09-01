package com.codi.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-06-22 11:25
 */
@Slf4j
public class BaseAppExceptionTest {
    @Test
    public void run() throws BaseAppException {

        BaseAppException e = new BaseAppException("1", "错误");

        throw e;
    }
}
