package com.codi.base.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 日志输出
 *
 * @author shi.pengyan
 * @version 1.0 2017-06-28 16:56
 * @since 1.0
 */
@Slf4j
public final class LogUtil {

    /**
     * 打印 Web Request 日志
     *
     * @param joinPoint
     */
    public static String makeLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest       request    = attributes.getRequest();

        StringBuilder sb = new StringBuilder("\n");

        sb.append("ip : ").append(request.getRemoteAddr()).append("\n");
        sb.append("args : ").append(Arrays.toString(joinPoint.getArgs())).append("\n");

        sb.append("Controller : ").append(joinPoint.getTarget().getClass().getName()).append(".(")
            .append(joinPoint.getTarget().getClass().getSimpleName()).append(".java:1) ").
            append(joinPoint.getSignature().getName()).append("\n");

        String uri = request.getRequestURI();
        if (uri != null) {
            sb.append("url : ").append(uri).append("\n");
        }

        Enumeration<String> e = request.getParameterNames();
        if (e.hasMoreElements()) {
            sb.append("Parameter   : ");
            while (e.hasMoreElements()) {
                String   name   = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    sb.append(name).append("=").append(values[0]);
                } else {
                    sb.append(name).append("[]={");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0)
                            sb.append(",");
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
                sb.append("  ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
