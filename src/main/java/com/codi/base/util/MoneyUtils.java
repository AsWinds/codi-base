package com.codi.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.alibaba.druid.util.StringUtils;

public class MoneyUtils {
    /** BigDecimal 0 */
    private static final BigDecimal BIG_DECIMAL_0 = new BigDecimal(0);
    /** BigDecimal 100 */
    private static final BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

    /**
     * 精确对比两个数字
     * 
     * @param v1
     *            需要被对比的第一个数
     * @param v2
     *            需要被对比的第二个数
     * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
     */
    public static int compareTo(BigDecimal b1, double v2) {
        BigDecimal b2 = new BigDecimal(v2);
        return b1.compareTo(b2);
    }

    /**
     * 默认保留2位小数
     * 
     * @param money
     * @return
     */
    public static String formatMoney(BigDecimal money) {
        return formatMoney(money, 2);
    }

    /**
     * 格式化金额
     * 
     * @param money
     * @param length
     * @return
     */
    public static String formatMoney(BigDecimal money, int length) {
        if (money == null) {
            return null;
        }
        NumberFormat formater = null;
        double num = money.doubleValue();
        if (length == 0) {
            formater = new DecimalFormat("###,###");
        } else {
            StringBuilder buff = new StringBuilder();
            buff.append("###,##0.");
            for (int i = 0; i < length; i++) {
                buff.append("0");
            }
            formater = new DecimalFormat(buff.toString());
        }
        String result = formater.format(num);

        return result;
    }

    /**
     * 保留两位小数，<font color="red">不四舍五入</font>
     * 
     * @param money
     *            金额
     * @param length
     *            保留的小数位数
     * @return
     */
    public static String formatMoneyNoRound(BigDecimal money, int length) {
        if (money == null) {
            return null;
        }

        StringBuilder buff = new StringBuilder();
        if (length == 0) {
            buff.append("###,###");
        } else {
            buff.append("###,##0.");
            for (int i = 0; i < length; i++) {
                buff.append("0");
            }
        }
        DecimalFormat formater = new DecimalFormat(buff.toString());
        formater.setMaximumFractionDigits(length);
        formater.setGroupingSize(0);
        if (money.compareTo(BIG_DECIMAL_0) >= 0) {
            formater.setRoundingMode(RoundingMode.FLOOR);
        } else {
            formater.setRoundingMode(RoundingMode.CEILING);
        }

        return formater.format(money.doubleValue());
    }

    /**
     * 转成百分比,<font color="red">不四舍五入</font>
     * 
     * @param money
     *            金额
     * @param length
     *            保留的小数位数
     * @return
     */
    public static String formatMoneyPercentNoRund(BigDecimal money, int length) {
        if (money == null) {
            return null;
        }

        money = money.multiply(BIG_DECIMAL_100);
        return formatMoneyNoRound(money, length);
    }

    /**
     * 反格式化金额字符串
     * 
     * @param formated
     * @return
     */
    public static String unformatMoney(String formated) {
        if (StringUtil.isEmpty(formated)) {
            return "";
        }

        return formated.replace(",", "");
    }

    public static BigDecimal getFundApplyFee(BigDecimal balance, BigDecimal ratio) {
        return balance.subtract(balance.divide(new BigDecimal(1).add(ratio), 2, RoundingMode.HALF_DOWN));
    }

    /**
     * 比较两个字符串数值
     * 
     * @param bigDecimalStr1
     *            bigDecimalStr
     * @param bigDecimalStr2
     *            bigDecimalStr
     * @return -1, 0, or 1 as this BigDecimal is numerically less than, equal
     *         to, or greater than val.
     */
    public static int compare(String bigDecimalStr1, String bigDecimalStr2) {
        BigDecimal before = new BigDecimal(bigDecimalStr1);
        BigDecimal after = new BigDecimal(bigDecimalStr2);
        return before.compareTo(after);
    }

    /**
     * 将收益率转换两位小数
     * 
     * @param value
     * @return
     */
    public static String parseRate(BigDecimal value) {
        return parseRate(value, 2);
    }

    /**
     * 将数值转成百分比，同时保留几位小数
     * 
     * @param value
     * @param decimalDigit
     * @return
     */
    public static BigDecimal convert2Percent(BigDecimal value, int decimalDigit) {
        if (value != null) {
            return value.multiply(BIG_DECIMAL_100).setScale(decimalDigit, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }

    /**
     * 格式化数字保留几位(银行家算法)
     * 
     * @param value
     *            原始值
     * @param decimalDigit
     *            保留几位小数
     * @return
     */
    public static BigDecimal format(BigDecimal value, int decimalDigit) {
        if (value != null) {
            return value.setScale(decimalDigit, BigDecimal.ROUND_HALF_EVEN);
        }
        return null;
    }

    /**
     * 将小数保留length位
     * 
     * @param value
     * @param length
     * @return
     */
    public static String parseRate(BigDecimal value, int length) {
        if (value == null) {
            return null;
        }
        return MoneyUtils.formatMoney(value, length);
    }

    public static String parseRate(String value, int length) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        BigDecimal money = new BigDecimal(value);

        return MoneyUtils.formatMoney(money, length);
    }

    /**
     * 将汇率转换成前端显示的
     * 
     * @param ratioStr
     *            原始汇率
     * @return
     */
    public static String calcRateToFrontEnd(String ratioStr) {
        BigDecimal ratio = new BigDecimal(ratioStr);
        return calcRateToFrontEnd(ratio);
    }

    /**
     * 将汇率转换成前端显示的百分比
     * 
     * @param value
     *            BigDecimal
     * @return
     */
    public static String calcRateToFrontEnd(BigDecimal value) {
        if (value == null) {
            return null;
        }
        value = value.multiply(BIG_DECIMAL_100);
        return MoneyUtils.formatMoney(value, 2);
    }

    /**
     * 计算折后的费率<br/>
     * 如果原始费率<=最小费率还小,则直接返回<br/>
     * 如果原始费率>最小费率大,则计算后，取min（折扣的费用,最小费率）
     * 
     * @param ratioStr
     *            原始费率
     * @param discountStr
     *            折扣率
     * @param minFareRatioStr
     *            最小折扣率
     * @return
     */
    public static String calcRate(String ratioStr, String discountStr, String minFareRatioStr) {
        BigDecimal ratio = new BigDecimal(ratioStr), discount = new BigDecimal(discountStr);
        BigDecimal minFareRatio = new BigDecimal(minFareRatioStr);

        if (ratio.compareTo(minFareRatio) <= 0) {
            return MoneyUtils.formatMoney(ratio.multiply(BIG_DECIMAL_100), 2);
        }

        BigDecimal result = ratio.multiply(discount);

        if (result.compareTo(BigDecimal.ZERO) == 0) {
            return MoneyUtils.formatMoney(result, 2);
        }
        if (result.compareTo(minFareRatio) == -1) { // less minFareRatio
            result = minFareRatio;
        }
        result = result.multiply(BIG_DECIMAL_100);
        return MoneyUtils.formatMoney(result, 2);
    }

    /**
     * 判断数值是否为0
     * 
     * @param value
     *            原始值
     * @return
     */
    public static boolean isZero(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return value.compareTo(BIG_DECIMAL_0) == 0;
    }

    /**
     * 判断数值是否为0
     * 
     * @param value
     *            原始字符串数值
     * @return
     */
    public static boolean isZero(String value) {
        if (StringUtil.isEmpty(value)) {
            return false;
        }
        return isZero(new BigDecimal(value));
    }

    /**
     * 相加
     * 
     * @param valueOne
     * @param valueTwo
     * @return
     */
    public static BigDecimal add(BigDecimal valueOne, BigDecimal valueTwo) {
        if (valueOne == null) {
            return valueTwo;
        } else {
            if (valueTwo == null) {
                return valueOne;
            } else {
                return valueOne.add(valueTwo);
            }
        }
    }

    /**
     * 相减
     * 
     * @param value1
     * @param value2
     * @return
     */
    public static BigDecimal subtract(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            if (value2 == null) {
                return null;
            } else {
                return value2.multiply(new BigDecimal(-1));
            }
        } else {
            if (value2 == null) {
                return value1;
            } else {
                return value1.subtract(value2);
            }
        }

    }

}
