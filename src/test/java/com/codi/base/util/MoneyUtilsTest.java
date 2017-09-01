package com.codi.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

/**
 * 金额工具类
 * 
 * @author shi.pengyan
 * @date 2016年9月26日 下午1:18:31
 */
public class MoneyUtilsTest {

    @Test
    public void formatMoneyTest() {
        String money = MoneyUtils.formatMoney(new BigDecimal("3.1465926"), 2);
        Assert.assertEquals("3.14", money);

        String money2 = MoneyUtils.formatMoney(new BigDecimal("0.1415926"), 2);
        Assert.assertEquals("0.14", money2);
    }

    /**
     * 不四舍五入
     */
    @Test
    public void formatMoney() {
        System.out.println("负数");
        double finalMoney = -9999.9999999;
        System.out.println(finalMoney);
        DecimalFormat formater = new DecimalFormat("###.00");
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(formater.format(finalMoney));

        System.out.println("正数");
        finalMoney = 9999.9999999;
        System.out.println(finalMoney);
        System.out.println(formater.format(finalMoney));

    }

    @Test
    public void formatMomeyNoRoundTest() {
        formatMoneyNoRound("99.99999", 2);
        formatMoneyNoRound("99.91999", 2);
        formatMoneyNoRound("99.9", 3);
        System.out.println("--------------");

        formatMoneyNoRound("-99.99999", 2);
        formatMoneyNoRound("-99.91999", 2);
        formatMoneyNoRound("-99.9", 3);

    }

    private void formatMoneyNoRound(String number, int length) {
        System.out.println(MoneyUtils.formatMoneyNoRound(new BigDecimal(number), length));
    }

    @Test
    public void test() {
        BigDecimal a = new BigDecimal("0.30100");
        // a.setScale(4);
        System.out.println(a.toString());
    }

}
