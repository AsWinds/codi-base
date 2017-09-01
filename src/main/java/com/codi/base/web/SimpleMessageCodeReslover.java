package com.codi.base.web;

import org.springframework.validation.MessageCodesResolver;

public class SimpleMessageCodeReslover implements MessageCodesResolver {

	@Override
	public String[] resolveMessageCodes(String errorCode, String objectName) {
		return new String[]{objectName + "." + errorCode};
	}

	@Override
	public String[] resolveMessageCodes(String errorCode, String objectName,
			String field, Class<?> fieldType) {
		return new String[]{objectName + "." + field + "." + errorCode};
	}

}
