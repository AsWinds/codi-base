package com.codi.base.converter;

import org.springframework.beans.BeanUtils;

public class Converter {
	
	public static <F, T> T convert(F from, Class<T> type){
		T result;
		try {
			result = type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		BeanUtils.copyProperties(from, result);
		return result;
	}
	

}
