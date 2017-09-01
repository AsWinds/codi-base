package com.codi.base.config;

import java.util.Properties;

/**
 * 配置接口，可以读取XML和Properties文件
 * 
 * @author shi.pengyan
 * @date 2016年10月4日 下午3:17:45
 */
public interface Configuration {

    String getString(String key);

    String getString(String key, String defaultValue);

    int getInt(String key);

    int getInt(String key, int defaultValue);

    long getLong(String key);

    long getLong(String key, long defaultValue);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

    Properties getProperties(String filename);

    Properties getproperties(String filename, Properties defaultProperties);
}
