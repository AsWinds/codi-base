package com.codi.base.config;

import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationMgrTest {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationMgr.class);

    @Test
    public void configurationMgrTest() {
        logger.debug(ConfigurationMgr.getConfigurationPath());
        logger.debug(ConfigurationMgr.getInstance().getString("jdbc.username"));
    }

    @Test
    public void config() {
        // ADD JVM args
        // java -DCODI_HOME=/abc/path
        Properties prop = System.getProperties();

        logger.debug(prop.getProperty("CODI_HOME"));
        logger.debug(prop.getProperty("user.home"));
        logger.debug(System.getenv("CODI_HOME"));
    }

    @Test
    public void getProperties() {
        ConfigurationMgr mgr = ConfigurationMgr.getInstance();

        Properties prop = mgr.getProperties("ufxsdk.properties");
        System.out.println(prop);

    }

}
