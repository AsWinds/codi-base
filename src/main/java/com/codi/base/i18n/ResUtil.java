package com.codi.base.i18n;

/**
 * 资源文件类
 * 
 * @author shi.pengyan
 * @date 2016年12月22日 下午1:09:04
 */
public final class ResUtil {

    /**
     * 获取自定义资源文件key:value
     * 
     * @param key
     *            key
     * @return
     */
    public static String get(String key) {
        return I18NMgr.getInstance().getValue(key);
    }

    /**
     * 获取自定义资源文件key:value
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public static String get(String key, String defaultValue) {
        return I18NMgr.getInstance().getValue(key, defaultValue);
    }
}
