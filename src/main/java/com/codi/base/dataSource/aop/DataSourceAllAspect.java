package com.codi.base.dataSource.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codi.base.dataSource.DataSourceContextHolder;

/**
 * 多数据源AOP，某个package下的所有方法
 * 
 * @author shi.pengyan
 * @date 2016年9月22日 下午3:34:25
 */
public class DataSourceAllAspect {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceAllAspect.class);

    private String dataSourceKey;

    public DataSourceAllAspect() {
    }

    public DataSourceAllAspect(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

    public void before(JoinPoint joinpoint) {
        logger.debug("[DataSource={}] before", this.dataSourceKey);
        DataSourceContextHolder.setContextType(this.dataSourceKey);
    }

    public void after(JoinPoint joinpoint) {
        logger.debug("[DataSource={}] after", this.dataSourceKey);
        DataSourceContextHolder.remove();
    }

}
