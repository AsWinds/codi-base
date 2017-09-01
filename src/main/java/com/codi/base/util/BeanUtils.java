package com.codi.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 功能描述：Map与Object转换
 *
 *
 */
@SuppressWarnings("rawtypes")
public class BeanUtils {
	
	/**
	 * 
	 * 功能描述：实现Object到Map的转换
	 *
	 *
	 * @param o
	 * @param map
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	
	@SuppressWarnings("unchecked")
	public static void ConvertToMap(Object vo, Map map){
		Class c=vo.getClass();
		Class superc = vo.getClass().getSuperclass();
		Field[] fs=c.getDeclaredFields();
		Field[] fss=superc.getDeclaredFields();
		Method method=null;
		//遍历所有的属性(包括私有属性)
		for(int i=0;i<fs.length;i++){
			String key=fs[i].getName();
			//组装get方法
			String m="get"+key.substring(0,1).toUpperCase()+key.substring(1);
			try {
				method=c.getMethod(m);
			} catch (NoSuchMethodException e) {
				continue;
			}
			//使用属性的get方法取值
		    Object value=null;
			try {
				value = method.invoke(vo);
			} catch (Exception e) {
				throw new RuntimeException("GetMehtod execute Exception");
			}
		    map.put(key, value);
		}
		
		for(int i=0;i<fss.length;i++){
			String key=fss[i].getName();
			//组装get方法
			String m="get"+key.substring(0,1).toUpperCase()+key.substring(1);
			try {
				method=c.getMethod(m);
			} catch (NoSuchMethodException e) {
				continue;
			}
			//使用属性的get方法取值
		    Object value=null;
			try {
				value = method.invoke(vo);
			} catch (Exception e) {
				throw new RuntimeException("GetMehtod execute Exception");
			}
		    map.put(key, value);
		}
	}
	/**
	 * 
	 * 功能描述：实现Object到Map的转换,Date类型处理成String
	 *
	 *
	 * @param o
	 * @param map
	 * @throws Exception
	 *
	 */
	@SuppressWarnings("unchecked")
	public static void ConvertToMapWithDate(Object vo, Map map){
		Class c=vo.getClass();
		Field[] fs=c.getDeclaredFields();
		Method method=null;
		//遍历所有的属性(包括私有属性)
		for(int i=0;i<fs.length;i++){
			String key=fs[i].getName();
			//组装get方法
			String m="get"+key.substring(0,1).toUpperCase()+key.substring(1);
			try {
				method=c.getMethod(m);
			} catch (NoSuchMethodException e) {
				continue;
			}
			//使用属性的get方法取值
		    Object value=null;
			try {
				value = method.invoke(vo);
				if(value!=null&&value.getClass().getName().equals("java.util.Date")){
					value=DateUtil.format((Date)value);
				}
			} catch (Exception e) {
				throw new RuntimeException("GetMehtod execute Exception",e);
			}
		    map.put(key, value);
		}
	}
	/**
	 * 
	 * 功能描述：实现Map到Object的转换,只处理String类型,Map中的Key与Object属性严格匹配
	 *
	 *
	 * @param o
	 * @param map
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * o{Name='aa'},map{Name:'aa'};
	 */
	@SuppressWarnings("unchecked")
	public static void ConvertToObject(Object o, Map<String,String> map) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Iterator iter = map.entrySet().iterator();
		Class c=o.getClass();
		Method[]  methods=c.getMethods();
		while (iter.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			for(int i=0;i<methods.length;i++){
				String methodName=methods[i].getName();
				if(methodName.toUpperCase().startsWith("SET")){
				  String m="SET"+entry.getKey().toUpperCase();
				  if(methodName.equalsIgnoreCase(m)){
					methods[i].invoke(o,entry.getValue());
				  }
				}
			}
		} 
	}
}