package com.codi.base.util;

import org.slf4j.Logger;

public class ExceptionUtil {
	// 记录错误
	public static void logError(Logger logger, String title, Exception exception) {
		logger.error(title, exception);
	}

	// 获取异常信息
	public static String getMessage(Exception exception) {
		if (exception != null) {
			return exception.getMessage();
		}
		return "";
	}
}
