package com.codi.base.util;

import java.util.List;

/**
 * ListUtil
 * 
 * @author shi.pengyan
 * @date 2016年9月30日 上午10:06:52
 */
public final class ListUtil {
    /**
     * 判断是否空
     * 
     * @param list
     *            list
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断是否不为空
     * 
     * @param list
     *            list
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(List list) {
        return list != null && !list.isEmpty();
    }
}
