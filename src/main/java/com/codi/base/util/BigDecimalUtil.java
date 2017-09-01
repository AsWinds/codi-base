package com.codi.base.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

	/**
	 * 默认值为0
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal ConvertToBigDecimal(String value) {
		return ConvertToBigDecimal(value, new BigDecimal(0));
	}

	/**
	 * 添加默认值入参
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal ConvertToBigDecimal(String value, BigDecimal defaultValue) {
		if (StringUtil.isEmpty(value)) {
			return defaultValue;
		}
		// 构造以字符串内容为值的BigDecimal类型的变量
		BigDecimal result = new BigDecimal(value);

		return result;
	}

	// 转字符串
	public static String ConvertToString(BigDecimal value) {
		return ConvertToString(value, 2);
	}

	// 转字符串
	public static String ConvertToString(BigDecimal value, Integer length) {
		// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
		value = value.setScale(length, BigDecimal.ROUND_HALF_UP);
		// 转化为字符串输出
		return value.toString();
	}
}
