package com.codi.base.dataSource.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codi.base.dataSource.DataSourceContextHolder;
import com.codi.base.dataSource.annotation.DataSource;
import com.codi.base.util.StringUtil;

/**
 * 多数据源AOP
 * 
 * @author shi.pengyan
 * @date 2016年9月22日 下午3:34:25
 */
public class DataSourceAspect {
    private static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 数据源切面<br>
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object aroundDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        // @Around("within(...service..*) && @annotation(tx)")

        Object obj = null;
        boolean isNotEmpty = false;
        try {
            logger.debug("begin datasource around : {}", joinPoint.toString());

            MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
            Method method = joinPointObject.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);

            isNotEmpty = StringUtil.isNotEmpty(dataSource.value());

            if (isNotEmpty) {
                DataSourceContextHolder.setContextType(dataSource.value());
            }
            obj = joinPoint.proceed(joinPoint.getArgs());

            // } catch (Exception e) {
            // logger.error("run fund_detail datasource", e);
        } finally {
            if (isNotEmpty) {
                DataSourceContextHolder.remove();
            }
            logger.debug("end dataSource around :{}", joinPoint.toString());
        }
        return obj;
    }
}
