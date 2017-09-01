package com.codi.base.util;

import java.math.BigDecimal;
import java.util.*;

public class MapUtils {
    /**
     * 将树形结构的Map所有节点按照键排序
     *
     * @param orgMap
     * @return
     */
    public static Map<String, Object> sortMapTree(Map<String, Object> orgMap) {
        return sortMapTree(orgMap, false);
    }

    /**
     * 将树形结构的Map所有节点按照键排序
     *
     * @param orgMap
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String, Object> sortMapTree(Map<String, Object> orgMap, Boolean isDebug) {
        // 初始化执行队列
        Queue queue = new LinkedList();
        Queue keyPathQ = new LinkedList();

        // 根节点下的元素排序
        Map<String, Object> rootSortMap = new TreeMap<String, Object>();
        rootSortMap.putAll(orgMap);

        if (isDebug)
            System.out.println("rootSortMap: " + rootSortMap.toString());

        for (String rootkey : rootSortMap.keySet()) {

            queue.add(rootSortMap.get(rootkey));
            keyPathQ.add(rootkey);

            while (!queue.isEmpty()) {
                // 提取队列中的对象
                Map<String, Object> childMap = (Map<String, Object>) queue.poll();
                String keyP = (String) keyPathQ.poll();

                if (isDebug)
                    System.out.println("[" + keyP + "] Map: " + childMap.toString());

                // 排序
                Map<String, Object> childSortMap = new TreeMap<String, Object>();
                childSortMap.putAll(childMap);

                // 将排序后的对象更新到对应节点上
                updateNodeOnMapTreeByKeyPath(rootSortMap, keyP, childSortMap);

                if (isDebug) {
                    System.out.println("childSortMap: " + childSortMap.toString());
                    System.out.println("[After this sort] rootSortMap: " + rootSortMap.toString());
                }

                // 将子节点加入到队列
                for (String childKey : childSortMap.keySet()) {
                    // 如果子节点是Map类型则加进队列
                    if (childMap.get(childKey) instanceof Map<?, ?>) {
                        queue.add(childMap.get(childKey));
                        System.out.println("keyP: " + keyP + " + " + childKey);
                        keyPathQ.add(keyP + "|" + childKey);
                    }
                }

            }

        }

        return rootSortMap;
    }

    @SuppressWarnings({ "unchecked" })
    private static void updateNodeOnMapTreeByKeyPath(Map<String, Object> rootMap, String Keypath,
            Map<String, Object> newNode) {
        Map<String, Object> map = rootMap;
        Map<String, Object> parentMap = null;
        String lastkey = null;
        for (String key : Keypath.split("\\|")) {
            parentMap = map;
            map = (Map<String, Object>) map.get(key);
            lastkey = key;
        }
        parentMap.put(lastkey, newNode);
    }

    public static Date getDate(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : (Date) value;
    }

    public static Date getDate(Map<String, Object> map, String key, Date defaultValue) {
        Date date = getDate(map, key);
        return date == null ? defaultValue : date;
    }

    public static String getStr(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : (String) value;
    }

    public static String getStr(Map<String, Object> map, String key, String defaultValue) {
        String value = getStr(map, key);
        return value == null ? defaultValue : value;
    }

    public static BigDecimal getBigDecimal(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : (BigDecimal) value;
    }

    public static BigDecimal getBigDecimal(Map<String, Object> map, String key, BigDecimal defaultValue) {
        BigDecimal value = getBigDecimal(map, key);
        return value == null ? defaultValue : value;
    }

    public static Integer getInteger(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : (Integer) value;
    }

    public static Integer getInteger(Map<String, Object> map, String key, Integer defaultValue) {
        Integer value = getInteger(map, key);
        return value == null ? defaultValue : value;
    }

    public static Long getLong(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : (Long) value;
    }

    public static Long getLong(Map<String, Object> map, String key, Long defaultValue) {
        Long value = getLong(map, key);
        return value == null ? defaultValue : value;
    }

    public static Boolean getBoolean(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : (Boolean) value;
    }

    public static Boolean getBoolean(Map<String, Object> map, String key, Boolean defaultValue) {
        Boolean value = getBoolean(map, key);
        return value == null ? defaultValue : value;
    }

    /**
     * 判断是否为null
     * @param map
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Boolean isEmpty(Map map){
        if(map == null || map.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 判断是否不为null
     * @param map
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Boolean isNotEmpty(Map map){
        return map!= null && !map.isEmpty();
    }


}
