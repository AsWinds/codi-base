package com.codi.base.dataSource;

/**
 * 数据源上下文持有类
 * 
 * @author shi.pengyan
 *
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setContextType(String type) {
		contextHolder.set(type);
	}

	public static String getContextType() {
		return contextHolder.get();
	}

	public static void remove() {
		contextHolder.remove();
	}
}
