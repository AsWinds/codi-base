package com.codi.base.i18n;

import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 文案方案
 * 
 * @author shi.pengyan
 * @date 2016年11月8日 下午2:26:07
 */
public class I18NMgr {

    private static final Logger logger = LoggerFactory.getLogger(I18NMgr.class);
    private static Properties props = new Properties();

    static {
        initI18N();
    }

    private I18NMgr() {
    }

    /**
     * 1.每个$CLASSPATH/i18n/*.properties文件<br/>
     * 2.可读取自定义$CODI_HOME/i18n/*.properties文件 //TODO
     */
    private static void initI18N() {
        synchronized (I18NMgr.class) {
            try {
                // Thread.currentThread().getContextClassLoader().getResourceAsStream("i18n/")

                ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
                Resource[] files = resourceResolver.getResources("classpath*:i18n/*.properties");
                
                if(files!= null){
                    for (Resource file : files) {
                        logger.debug("read msg file={}", file.getURL());
                        InputStreamReader input = new InputStreamReader(file.getInputStream(), "UTF-8");

                        props.load(input);
                        input.close();
                    }
                }
            } catch (Exception e) {
                logger.error("Fail to load i18n/*.properties", e);
            }
        }

    }

    public static synchronized I18NMgr getInstance() {
        return I18NMgrHolder.getI18NMgr();
    }

    public String getValue(String key) {
        return getValue(key, "Unknow Key=" + key);
    }

    public String getValue(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    private static class I18NMgrHolder {
        private static I18NMgr mgr = null;

        private I18NMgrHolder() {
        }

        public static I18NMgr getI18NMgr() {
            if (mgr == null) {
                mgr = new I18NMgr();
            }
            return mgr;
        }

    }

}
