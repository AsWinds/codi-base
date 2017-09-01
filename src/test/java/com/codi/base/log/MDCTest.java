package com.codi.base.log;

import com.codi.base.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.MDC;

import java.util.concurrent.CountDownLatch;

/**
 * mdc 具体的实现类，由log4j或logback来实现
 * log4j 可以正常输出，内部自己实现了
 * logback 低版本可以使用，在高版本中使用了ThreadLocal替换了InheritThreadLocal
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-21 10:14
 * @since 1.0
 */
@Slf4j
public class MDCTest {
    @Test
    public void log4jTest() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        MDC.put(Const.GLOBAL_MDC_ID, "778899");

        log.debug("main thread");

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("thread 2");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        log.debug("thread 3");

                        latch.countDown();
                    }
                }).start();

            }
        }).start();

        latch.await();

    }
}
