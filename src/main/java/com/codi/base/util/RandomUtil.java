package com.codi.base.util;

import java.util.Random;

public class RandomUtil {
	public static String getNumRandom(int length, boolean isReadom) {
		Random random = new Random();
		String base = "0123456789";
		int size = base.length();
		StringBuilder randomCode = new StringBuilder();

		if (isReadom) {
			// 随机产生4位数字的验证码。
			for (int i = 0; i < length; i++) {
				// 得到随机产生的验证码数字。
				int start = random.nextInt(size);
				String strRand = base.substring(start, start + 1);

				// 将产生的四个随机数组合在一起。
				randomCode.append(strRand);
			}
		} else {
			randomCode.append("123456");
		}

		return randomCode.toString();
	}
}
