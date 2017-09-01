package com.codi.base.log.desensitizer;

import javax.servlet.http.HttpServletRequest;

import com.codi.base.log.annotation.Loggable;

/**
 * 方法日志记录, 因为aop不能拦截静态方法，所以在这里中转下,会用在ResponseUtils和GlobalExceptionHandler
 * @author Shangdu Lin
 *
 */
public class LogProxy {
    
    @Loggable
    public void data(HttpServletRequest request,Object response){             
    }
}
