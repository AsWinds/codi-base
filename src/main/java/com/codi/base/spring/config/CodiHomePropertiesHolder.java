package com.codi.base.spring.config;

import com.codi.base.config.ConfigurationMgr;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * 将CODI_HOME下变量注入Spring容器中
 *
 * @author shi.pengyan
 * @date 2017年2月6日 下午1:56:18
 */
@Slf4j
public class CodiHomePropertiesHolder {
    private String[] filenames;

    /**
     * 返回合并后的Properties
     *
     * @return
     */
    public Properties getProperties() {
        Properties result = new Properties();
        ConfigurationMgr configMgr = ConfigurationMgr.getInstance();
        for (String filename : filenames) {
            Properties p = configMgr.getProperties(filename);
            log.debug("filename={}, props={}", filename, p);
            if (p == null || p.isEmpty()) {
                log.debug("prop is null");
            } else {
                result.putAll(p);
            }
        }

        return result;
    }

    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }
}

