package com.codi.base.web.filter.spring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.codi.base.domain.BaseResult;
import com.codi.base.exception.AbstractException;

import lombok.extern.slf4j.Slf4j;

/**
 * 需要Spring Bean支持, 强依赖FastJson的Spring配置
 * 
 * */
@Slf4j
@Component
public class ExceptionTranslationFilter implements Filter  {
	
	@Autowired
	private FastJsonHttpMessageConverter4 converter;
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
	    
    }

	@Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
	    try {
	        chain.doFilter(request, response);
        } catch (Exception e) {
	        if (e instanceof AbstractException) {
	        	AbstractException be = (AbstractException) e;
	            BaseResult result = be.toBaseResult();
	            HttpServletResponse resp = (HttpServletResponse) response;
	            converter.write(result, MediaType.APPLICATION_JSON_UTF8, new ServletServerHttpResponse(resp));
	            return;
            }
	        log.error("Unexpect exception", e);
        }
    }

	@Override
    public void destroy() {
	    
    }
	


}
