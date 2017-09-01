/**
 * BizException.java	  V1.0   2012-6-3 下午3:49:26
 *
 * Copyright Talkweb Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.codi.base.domain;

import java.util.Map;

public class BizException {

	public static final Map<String, String> Exceptions = new java.util.HashMap<String, String>();
	
	static {
		Exceptions.put("00", "操作成功!");
		Exceptions.put("99", "操作失败!");
		Exceptions.put("98", "优惠券不存在!");
		Exceptions.put("97", "优惠券已过期!");
		Exceptions.put("96", "优惠券已使用!");
		Exceptions.put("95", "优惠券正在使用中,取消后方能再次使用!");
		Exceptions.put("94", "优惠券未激活!");
		Exceptions.put("93", "优惠券已废弃!");
		Exceptions.put("92", "优惠券状态异常!!");
		Exceptions.put("91", "您所使用的优惠券不能在此场次使用!");
		Exceptions.put("90", "一笔订单仅能使用一张现金券!");
		Exceptions.put("89", "商品被列入次券与抵价券黑名单，无法进行优惠活动!");
		Exceptions.put("88", "次券与抵值券仅能用于订座票!");
		Exceptions.put("87", "抵值或次券仅能一张券对应一张票!");
		Exceptions.put("86", "一笔订单仅能用一种类型的优惠券!");
		Exceptions.put("85", "次券面值要小于商品单价，不能进行兑换!");
		Exceptions.put("84", "很抱歉，该优惠券不支持购买该商品（票）!");
		Exceptions.put("83", "很抱歉，该优惠活动未设置影院结算价，无法正常进行活动!");
		Exceptions.put("82", "很抱歉，次券面值要小于商品最低限价，无法进行兑换!");
		Exceptions.put("80", "很抱歉，优惠券限制使用该类型的影片!");
		Exceptions.put("79", "很抱歉，订单并不存在!");
	}
	
	public static String getErrorDes(String errorCode){
		return Exceptions.get(errorCode);
	}
}
