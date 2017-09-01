/*
 * Copyright (c) 2007-2009 talkWeb All rights reserved.
 * History: 
 * Date          Modified_By    Reason    Description 
 * 2012-02-01    xiaowenbin       初建      初建
 */
package com.codi.base.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 配置文件工具读取类
 * 
 * @author Shangdu Lin
 * 
 */

public abstract class PropertiesUtil {

	private static java.util.Map<String, java.util.Map<String, String>> allProperties = new java.util.HashMap<String, java.util.Map<String, String>>();

	/**
	 * 读取一个配置文件中的一个值
	 * 
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String doGetKeyValue(String fileName, String key) {
		String value = null;
		java.util.Map<String, String> map = allProperties.get(fileName.toLowerCase());
		if (map == null) {
			initProperties(fileName);
			map = allProperties.get(fileName);
		}
		if (map != null) {
			value = map.get(key);
		}
		return value;
	}

	/**
	 * 读取一个配置文件中的全部值
	 * 
	 * @param fileName
	 * @return
	 */
	public static java.util.Map<String, String> doGetProperties(String fileName) {
		java.util.Map<String, String> map = allProperties.get(fileName);
		if (map == null) {
			initProperties(fileName);
			map = allProperties.get(fileName);
		}
		return map;
	}	
	
	//转成Map
	public static java.util.Map<String, String> doGetProperties(java.util.Properties prop) {
		java.util.Map<String, String> map = new java.util.HashMap<String, String>();
		if (prop.size() > 0) {
			@SuppressWarnings("rawtypes")
			java.util.Iterator iter = prop.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = prop.getProperty(key);
				map.put(key, value);
			}
		}
		
		return map;
	}	
	

	/**
	 * 读取配置文件
	 * 
	 * @param fileName
	 */
	private synchronized static void initProperties(String fileName) {
		java.util.Properties prop = new java.util.Properties();
		java.util.Map<String, String> map = new java.util.HashMap<String, String>();
		try {
			// prop.load(PropertiesUtil.class.getClassLoader().getResource(fileName).openStream());
			prop.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),
					"UTF-8"));
			if (prop.size() > 0) {
				@SuppressWarnings("rawtypes")
				java.util.Iterator iter = prop.keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					String value = prop.getProperty(key);
					map.put(key, value);
				}
			}
			allProperties.put(fileName, map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Map<String, String> map = PropertiesUtil.doGetProperties("message.properties");
		System.out.println(map);
	}
}
