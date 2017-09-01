package com.codi.base.web.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import com.codi.base.config.ConfigurationMgr;
import com.codi.base.util.EqualsUtil;
import com.codi.base.util.StringUtil;

/**
 * 应用级Listener启动
 * 
 * @author shi.pengyan
 * @date 2016年10月21日 上午11:03:51
 */
public class CodiBootstrapListener implements ServletContextListener {

    private static final String PARAM_WEB_APP_NAME = "WEB_APP_NAME";

    private static final String DEFAULT_LOG4J_FILE = "log4j.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConfigurationMgr configMgr = ConfigurationMgr.getInstance();

        String logConfigFile = loadLog4jFile(sce.getServletContext());

        long checkInterval = configMgr.getLong("codi.log4j.checkInterval", 10000L);
        if (EqualsUtil.equals(checkInterval, 0)) {
            PropertyConfigurator.configure(logConfigFile);
        } else {
            PropertyConfigurator.configureAndWatch(logConfigFile, checkInterval);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LogManager.shutdown();
    }

    /**
     * 加载log4j配置文件
     * 
     * @param ctx
     * @return
     */
    private String loadLog4jFile(ServletContext ctx) {
        String webAppName = ctx.getInitParameter(PARAM_WEB_APP_NAME);

        StringBuilder filePath = new StringBuilder(ConfigurationMgr.getConfigurationPath());
        filePath.append(File.separator).append("log4j");

        if (StringUtil.isNotEmpty(webAppName)) {
            String basePath = filePath.toString();
            filePath.append(File.separator).append("log4j-").append(webAppName).append(".properties");
            File file = new File(filePath.toString());
            if (file.exists()) {
                return filePath.toString();
            } else {
                return basePath + File.separator + DEFAULT_LOG4J_FILE;
            }
        } else {
            filePath.append(File.separator).append(DEFAULT_LOG4J_FILE);
        }

        return "";
    }

}
