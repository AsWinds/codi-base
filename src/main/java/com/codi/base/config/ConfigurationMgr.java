package com.codi.base.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codi.base.util.FileUtil;
import com.codi.base.util.StringUtil;

/**
 * 读取配置文件
 * 
 * @author shi.pengyan
 * @date 2016年10月4日 下午3:15:13
 */
public class ConfigurationMgr implements Configuration {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationMgr.class);

    private static String configPath = null;
    private static Properties props = new Properties();
    private static Map<String, Properties> propsCache = new ConcurrentHashMap<>();

    static {
        initConfigPath();
        loadConfig();
        logger.debug("CODI_HOME={}", configPath);
    }

    /**
     * 初始化配置文件路径 优先级：JVM args > ${user.home}/CODI_HOME > /opt/CODI_HOME
     */
    private static void initConfigPath() {
        Properties prop = System.getProperties();
        String userHome = prop.getProperty("user.home", "/opt");
        configPath = prop.getProperty("CODI_HOME", userHome + "/CODI_HOME");
    }

    /**
     * 暂时只支持properties文件读取
     */
    private static void loadConfig() {

        // 只有loadConfig ok后才能 getInstance();
        synchronized (ConfigurationMgr.class) {
            File configDir = new File(configPath);

            File[] propertyFiles = FileUtil.filter(configDir, ".properties");
            if (propertyFiles != null) {
                for (File file : propertyFiles) {
                    try {
                        InputStream input = new FileInputStream(file);

                        if (!input.markSupported()) {
                            input = new BufferedInputStream(input);
                        }

                        input.mark(input.available());
                        Properties prop = new Properties();
                        prop.load(input);
                        propsCache.put(file.getName(), prop);
                        
                        input.reset();                       
                        props.load(input);
                        
                        input.close();
                        logger.debug("config load {} successfully.", file.getCanonicalPath());
                    } catch (FileNotFoundException e) {
                        logger.error("file not found.", e);
                    } catch (IOException e) {
                        logger.error("io excepion", e);
                    }
                }
            } else {
                logger.error("configPath[{}] is not exist", configPath);
            }
        }
    }

    private ConfigurationMgr() {
    }

    public static synchronized ConfigurationMgr getInstance() {
        return ConfigurationMgrHolder.getConfigurationMgr();
    }

    public static String getConfigurationPath() {
        return configPath;
    }

    @Override
    public String getString(String key) {
        String value = props.getProperty(key);
        logger.debug("{}={}", key, value);
        if (value != null) {

            return value.trim();
        }
        return null;
    }

    @Override
    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        String value = getString(key);
        if (StringUtil.isNotEmpty(value)) {
            return Integer.valueOf(value);
        }
        return 0;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (StringUtil.isNotEmpty(value)) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    @Override
    public long getLong(String key) {
        String value = getString(key);
        if (StringUtil.isNotEmpty(value)) {
            return Long.valueOf(value);
        }
        return 0;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        String value = getString(key);
        if (StringUtil.isNotEmpty(value)) {
            return Long.valueOf(value);
        }
        return defaultValue;
    }

    @Override
    public boolean getBoolean(String key) {
        return Boolean.valueOf(getString(key));
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key);
        if (StringUtil.isNotEmpty(value)) {
            return Boolean.valueOf(value);
        }

        return defaultValue;
    }

    @Override
    public Properties getProperties(String filename) {
        return propsCache.get(filename);
    }

    @Override
    public Properties getproperties(String filename, Properties defaultProperties) {
        Properties props = propsCache.get(filename);
        if (props == null) {
            return defaultProperties;
        }
        return props;
    }

    private static class ConfigurationMgrHolder {

        private static ConfigurationMgr mgr = null;

        private ConfigurationMgrHolder() {
        }

        public static ConfigurationMgr getConfigurationMgr() {
            if (mgr == null) {
                mgr = new ConfigurationMgr();
            }
            return mgr;
        }

    }
}
