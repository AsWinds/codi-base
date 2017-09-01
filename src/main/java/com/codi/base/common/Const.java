package com.codi.base.common;

/**
 * 框架常量
 *
 * @author shi.pengyan
 * @date 2016年11月21日 下午12:54:27
 */
public interface Const {

    /**
     * 最后更新时间
     **/
    String LAST_UPDATE_TIME = "LAST_UPDATE_TIME";

    /**
     * 登陆用户标识
     **/
    String SESSION_LOGIN_USER = "SESSION_LOGIN_USER";

    /* 是否是开发模式 */
    String IS_DEVELOPMENT = "codi.isDevelopment";

    // 系统异常错误
    Integer ERROR_SYS_EXCEPTION = 1022;

    // 非法请求
    Integer ERROR_INVALID_REQUEST = 1023;

    /**
     * App-Device头
     */
    String HEADER_APP_DEVICE = "App-Device";

    /* 全局MDC标识 */
    String GLOBAL_MDC_ID = "GLOBAL_MDC_ID";

    /* 用户token标识 */
    String GLOBAL_USER_TOKEN = "Auth-Token";
}
