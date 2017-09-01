package com.codi.base.util;

public abstract class BizAssert {

	
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
	public static void notEmpty(String s, String message) {
		if (s == null || "".equals(s)) {
			throw new IllegalArgumentException(message);
		}
	}
}
