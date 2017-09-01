package com.codi.base.spring.interceptor;

import com.codi.base.common.Const;
import com.codi.base.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * MDC
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-20 9:51
 * @since 1.0
 */
@Slf4j
public class MDCInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Const.GLOBAL_USER_TOKEN);
        if (StringUtil.isEmpty(token)) {
            token = UUID.randomUUID().toString();
            log.info("token is null, create requestID");
        }

        MDC.put(Const.GLOBAL_MDC_ID, token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }
}
