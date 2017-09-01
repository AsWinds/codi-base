package com.codi.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @date 2017-06-22 14:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-application.xml"})
public class BaseSpringTest {

}
