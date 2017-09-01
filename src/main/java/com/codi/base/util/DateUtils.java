package com.codi.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间处理类
 * 
 * 
 */
public class DateUtils {

    private static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmss_plain = "yyyyMMddHHmmss";
    /** MM-dd **/
    public static final String MM_dd = "MM-dd";

    public static final String yyyyMMdd = "yyyy-MM-dd";

    /****/
    public final static int DIFFER_IN_SECOND = 0;
    /****/
    public final static int DIFFER_IN_MINUTE = 1;
    /****/
    public final static int DIFFER_IN_HOUR = 2;
    /****/
    public final static int DIFFER_IN_DAYS = 3;

    /**
     * 
     * 获取当前日期时间(格式yyyy-MM-dd HH:mm:ss,SSS)
     */
    public static String getFullNormalFormatTime() {
        java.util.Date currentTime = new Date();
        String timestr = "";
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        timestr += formatter.format(currentTime);
        return timestr;
    }

    /**
     * 打印信息到控制台 前面添加(格式HH:mm:ss,SSS)
     */
    public static String getNormalFormatTime() {
        java.util.Date currentTime = new Date();
        String timestr = "";
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        timestr += formatter.format(currentTime);
        return timestr;
    }

    /**
     * 获取当前日期时间
     * 
     * @return 当前日期时间字符串(格式yyyy-MM-dd HH:mm:ss)
     */
    public static String getNormalFormatDateTime() {
        java.util.Date currentTime = new Date();
        String timestr = "";
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestr += formatter.format(currentTime);
        return timestr;
    }

    /**
     * 获取当前日期时间
     * 
     * @return 当前日期时间字符串(格式yyMMddHHmmss)
     */
    public static String getPureNumDateTime() {
        java.util.Date currentTime = new Date();
        String timestr = "";
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyMMddHHmmss");
        timestr += formatter.format(currentTime);
        return timestr;
    }

    public static Date addSecond(Date date, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 时间格式转换
     * 
     * @param ad_source
     * @param as_format
     * @return
     */
    public static String translateDate(Date ad_source, String as_format) {
        if (ad_source == null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat(as_format);

        return sdf.format(ad_source);
    }

    /**
     * 把字符窜转换成日期
     * 
     * @param as_date
     * @return
     */
    public static Date stringToDate(String as_date, String as_format) {
        try {
            SimpleDateFormat target = new SimpleDateFormat(as_format);
            if (as_date == null || "".equals(as_date)) {
                return null;
            }
            return target.parse(as_date);
        } catch (Exception ex) {
            // ex.printStackTrace();
            return null;
        }
    }

    /**
     * ToDo:将完整的date类型转换为需要的date类型。
     * 
     * @param change_str
     * @param to_format
     * @return
     */
    public static Date dateFormatChange(Date change_str, String to_format) {
        Date getDate;
        try {
            SimpleDateFormat dateChange = new SimpleDateFormat(to_format);
            getDate = stringToDate(dateChange.format(change_str), to_format);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("date Format change error :" + e);
            return null;
        }
        return getDate;
    }

    /**
     * 把字符日期重新以to_format的格式重新格式化
     * 
     * @param strDate
     * @param to_format
     * @return
     */
    public static String dateStrFormatChanage(String strDate, String to_format) {
        String theDate;
        try {
            Date date = stringToDate(strDate, to_format);
            theDate = translateDate(date, to_format);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("string date format change error: " + ex);
            return null;
        }
        if (theDate == null)
            theDate = "";
        return theDate;
    }

    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    public static String getFormatedDate(Date date, String format) {
        Date now = date;
        SimpleDateFormat outFormat = new SimpleDateFormat(format);
        String s = outFormat.format(now);
        return s;
    }

    public static Date addMondth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * Formats a Date object to return a date using the global locale.
     */
    public static String formatDate(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
        return outFormat.format(date);
    }

    /**
     * Formats a Date object to return a date and time using the global locale.
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return outFormat.format(date);
    }

    public static String formatDate2(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(myDate);
        return strDate;
    }

    public static String formatDate3(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        String strDate = formatter.format(myDate);
        return strDate;
    }

    public static String formatTime(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String strDate = formatter.format(myDate);
        return strDate;
    }

    public static String formatDate4(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(myDate);
        return strDate;
    }

    public static String formatDate5(Date myDate) {
        String strDate = getYear(myDate) + "-" + getMonth(myDate) + "-" + getDay(myDate);
        return strDate;
    }

    public static String formatDate6(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = formatter.format(myDate);
        return strDate;
    }

    public static String formatDate7(Date myDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        String strDate = formatter.format(myDate);
        return strDate;

    }

    public static String getDate(String aMask) {
        String ret = null;
        String mask = aMask;
        if (mask == null || "".equals(mask))
            mask = "yyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        ret = sdf.format(Calendar.getInstance().getTime());
        return ret;
    }

    public static long Date2Long(int year, int month, int date) {
        Calendar cld = Calendar.getInstance();
        month = month - 1;
        cld.set(year, month, date);
        return cld.getTime().getTime();
    }

    public static long Time2Long(int year, int month, int date, int hour, int minute, int second) {
        Calendar cld = Calendar.getInstance();
        month = month - 1;
        cld.set(year, month, date, hour, minute, second);
        return cld.getTime().getTime();
    }

    public static int getYear(long t) {
        Calendar cld = Calendar.getInstance();
        if (t > 0) {
            cld.setTime(new java.util.Date(t));
        }
        return cld.get(Calendar.YEAR);
    }

    public static int getMonth(long t) {
        Calendar cld = Calendar.getInstance();
        if (t > 0) {
            cld.setTime(new java.util.Date(t));
        }
        return cld.get(Calendar.MONTH) + 1;
    }

    public static int getDay(long t) {
        Calendar cld = Calendar.getInstance();
        if (t > 0) {
            cld.setTime(new java.util.Date(t));
        }
        return cld.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(long t) {
        Calendar cld = Calendar.getInstance();
        if (t > 0) {
            cld.setTime(new java.util.Date(t));
        }
        return cld.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(long t) {
        Calendar cld = Calendar.getInstance();
        if (t > 0) {
            cld.setTime(new java.util.Date(t));
        }
        return cld.get(Calendar.MINUTE);
    }

    public static int getSecond(long t) {
        Calendar cld = Calendar.getInstance();
        if (t > 0) {
            cld.setTime(new java.util.Date(t));
        }
        return cld.get(Calendar.SECOND);
    }

    public static int getYear(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.SECOND);
    }

    public static int getYear() {
        Calendar cld = Calendar.getInstance();
        cld.setTime(new java.util.Date());
        return cld.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar cld = Calendar.getInstance();
        cld.setTime(new java.util.Date());
        return cld.get(Calendar.MONTH) + 1;
    }

    public static int getDay() {
        Calendar cld = Calendar.getInstance();
        cld.setTime(new java.util.Date());
        return cld.get(Calendar.DAY_OF_MONTH);
    }

    public static long getLongTime() {
        return System.currentTimeMillis();
    }

    /**
     * dateToString(Date inDate) 把日期型转换成字符型"yyyy-MM-dd HH:mm:ss"
     * 
     * @param inDate
     *            Date
     * @return String
     */
    public static String dateToString(Date inDate) {
        String outDateStr = "";
        if (inDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outDateStr = formatter.format(inDate);
        }
        return outDateStr;
    }

    /***************************************************************************
     * dateToSimpleStr 把日期型转换成字符型"yyyy-MM-dd"
     * 
     * @param inDate
     *            Date 需要转换的日期时间
     * @return outDateStr String
     **************************************************************************/
    public static String dateToSimpleStr(Date inDate) {
        String outDateStr = "";
        if (inDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            outDateStr = formatter.format(inDate);
        }
        return outDateStr;
    }

    /***************************************************************************
     * dateToTime 把日期型转换成字符型""HH:mm:ss"
     * 
     * @param inDate
     *            Date 需要转换的日期时间
     * @return outDateStr String
     **************************************************************************/
    public static String dateToTime(Date inDate) {
        String outDateStr = "";
        if (inDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            outDateStr = formatter.format(inDate);
        }
        return outDateStr;
    }

    /***************************************************************************
     * stringToDateWithTime 把字符型"yyyy-MM-dd HH:mm:ss"转换成日期型
     * 
     * @param s
     *            String 需要转换的日期时间字符串
     * @return theDate Date
     **************************************************************************/
    public static Date stringToDateWithTime(String s) {
        Date theDate = new Date();
        try {
            if (s != null) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                theDate = dateFormatter.parse(s);
            } else {
                theDate = null;
            }
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return theDate;
    }

    /***************************************************************************
     * stringToDate 把字符型"yyyy-MM-dd"转换成日期型
     * 
     * @param s
     *            String 需要转换的日期时间字符串
     * @return theDate Date
     **************************************************************************/
    public static Date stringToDate(String s) {
        Date theDate = new Date();
        try {
            if (s != null) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                theDate = dateFormatter.parse(s);
            } else {
                theDate = null;
            }
        } catch (ParseException pe) {
            // pe.printStackTrace();
            theDate = null;
        }
        return theDate;
    }

    /***************************************************************************
     * dayOfWeek 获得星期几
     * 
     * @param inDate
     *            Date 原日期
     * @param AddDateInt
     *            int 要加减的天数
     * @return dayOfWeek int 获得星期几
     **************************************************************************/
    public static int dayOfWeek(Date inDate) {
        int dayOfWeek = 0;
        Calendar theCalendar = new GregorianCalendar();
        String DateStr = dateToString(inDate);
        theCalendar.set(Integer.parseInt(DateStr.substring(0, 4)), Integer.parseInt(DateStr.substring(5, 7)) - 1,
                Integer.parseInt(DateStr.substring(8, 10)));
        dayOfWeek = theCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 7) {
            dayOfWeek = 0;
        }
        return dayOfWeek;
    }

    /***************************************************************************
     * minusDate 计算两个日期的相隔天数
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            开始日期
     * @return result long
     **************************************************************************/
    public static long minusDate(Date beginDate, Date endDate) {
        long result = (beginDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);
        return result;
    }

    /***************************************************************************
     * getAge
     * 
     * @deprecated 根据生日计算年龄，周岁
     * @param brithday
     *            生日的字符串(yyyy-mm-dd)
     * @return age int
     **************************************************************************/
    @Deprecated
	public static int getAge(String brithday) {
        int age = 0;
        try {
            Calendar birth = Calendar.getInstance();
            int year = Integer.parseInt(brithday.substring(0, 4));
            int month = Integer.parseInt(brithday.substring(5, 7)) - 1;
            int day = Integer.parseInt(brithday.substring(8, 10));
            birth.set(year, month, day);
            Calendar today = Calendar.getInstance();
            if (today.get(Calendar.MONTH) > birth.get(Calendar.MONTH)
                    || (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH))
                    && today.get(Calendar.DATE) >= birth.get(Calendar.DATE))
                age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            else
                age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) - 1;
        } catch (Exception ex) {
            log.info("Error code:" + ex);
        }
        return age;
    }

    /***************************************************************************
     * dateStrToLong
     * 
     * @see 2005-11-15
     * @param dateStr
     *            日期字符串yyyy-mm-dd
     * @return long theDay
     **************************************************************************/
    public static long dateStrToLong(String dateStr) {
        long theDate = getLongTime();
        Date thisDate = stringToDate(dateStr);
        if (thisDate != null) {
            theDate = thisDate.getTime();
        }
        return theDate;
    }

    /***************************************************************************
     * dateStrWithTimeToLong
     * 
     * @param dateStr
     *            日期字符串yyyy-MM-dd HH:mm:ss
     * @return long theDay
     **************************************************************************/
    public static long dateStrWithTimeToLong(String dateStr) {
        long theDate = getLongTime();
        Date thisDate = stringToDateWithTime(dateStr);
        if (thisDate != null) {
            theDate = thisDate.getTime();
        }
        return theDate;
    }

    /***************************************************************************
     * longToDateStr
     * 
     * @param long theDateLong
     * @return String DateStr yyyy-MM-dd
     **************************************************************************/
    public static String longToDateStr(long theDateLong) {
        String dateStr = "1970-01-01";
        try {
            dateStr = Integer.toString(getYear(theDateLong)) + "-" + getMonth(theDateLong) + "-" + getDay(theDateLong);
        } catch (Exception ex) {
            log.info("error code:" + ex);
        }
        return dateStr;
    }

    /***************************************************************************
     * longToDateStr
     * 
     * @param long theDateLong
     * @return String DateStr yyyy-MM-dd
     **************************************************************************/
    public static String longToDateWithTimeStr(long theDateLong) {
        String dateStr = "1970-01-01 00:00:00";
        try {
            dateStr = Integer.toString(getYear(theDateLong)) + "-" + getMonth(theDateLong) + "-" + getDay(theDateLong)
                    + " " + getHour(theDateLong) + ":" + getMinute(theDateLong) + ":" + getSecond(theDateLong);
        } catch (Exception ex) {
            log.info("error code:" + ex);
        }
        return dateStr;
    }

    /**
     * 返回参数所给出的月份中的第一天是星期几
     * 
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public static int getWeekOfMonthFirstDay(Date date, boolean isChina) {
        int[] months = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = (year - 1) * 365;

        for (int i = 1; i < year; i++) {
            if (((i % 4 == 0) && (i % 100 != 0)) || ((i % 100 == 0) && i % 400 == 0))
                day = day + 1;
        }

        if (month > 2 && ((year % 4 == 0) && (year % 100 != 0)) || ((year % 100 == 0) && year % 400 == 0))
            day = day + 1;

        for (int i = 0; i < month - 1; i++)
            day = day + months[i];

        day = day + 1;

        int week = day % 7;
        if (!isChina) {
            week = week - 1 == -1 ? 6 : week - 1;
        }
        return week;
    }

    /**
     * 返回所给月份中有多少天
     * 
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public static int getDayOfMonth(int year, int month) throws Exception {
        if (year < 0 || month < 1 || month > 12) {
            throw new Exception("error year or month!");
        }
        int day = 0;
        switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            day = 31;
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            day = 30;
            break;
        case 2:
            if (((year % 4 == 0) && (year % 100 != 0)) || ((year % 100 == 0) && year % 400 == 0)) {
                day = 29;
            } else {
                day = 28;
            }
            break;
        }
        return day;
    }

    // 与数据库中时间格式对应，便于比较大小
    public static String getStartDateStr(String DateStr) {
        if (null == DateStr || "".equals(DateStr))
            return null;
        String s = " 00:00:00";
        String result = null;
        try {
            result = dateToString(stringToDateWithTime(DateStr + s));
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    // 与数据库中时间格式对应，便于比较大小
    public static String getEndDateStr(String DateStr) {
        if (null == DateStr || "".equals(DateStr))
            return null;
        String s = " 23:59:59";
        String result = null;
        try {
            result = dateToString(stringToDateWithTime(DateStr + s));
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static Date stringToDate2(String s, String format) throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        Date theDate = dateFormatter.parse(s);
        return theDate;
    }
    
    public static Date stringToDate4(String s) throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        Date theDate = dateFormatter.parse(s);
        return theDate;
    }

    /**
     * 
     * 功能描述：获取订单剩余时间(秒)
     * <p>
     * 修改历史 ：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    public static long getSurplusTime(Date orderTime, int minute) {
        long milliseconds = 0;

        Date nowtime = new Date();
        long longtime1 = orderTime.getTime() + minute * 60 * 1000;
        long longtime2 = nowtime.getTime();
        if (longtime1 > longtime2) {
            milliseconds = (longtime1 - longtime2) / 1000;
        }
        return milliseconds;
    }

    /**
     * 将date转换成String
     * 
     * @param timestamp
     * @return
     */
    public static String dateToStr(Date date, String aMask) {
        String ret = null;
        String mask = aMask;
        if (mask == null || "".equals(mask))
            mask = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        ret = sdf.format(date);
        return ret;
    }

    /**
     * 
     * 功能描述：获取多少个月的日期
     *
     * @author Shangdu Lin
     *         <p>
     *         创建日期 ：2012-11-2 上午9:46:15
     *         </p>
     *
     * @param date
     * @param afterDay
     * @return
     *
     *         <p>
     *         修改历史 ：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @SuppressWarnings("static-access")
    public static Date getAfterMonth(Date date, int afterMonth) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MONDAY, afterMonth);
        calendar.getTime();
        return calendar.getTime();
    }

    /*
     * 取本周周五的时间
     */
    public static Date getNowWeekFriday() {
        int fridayPlus;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 5) {
            fridayPlus = 0;
        } else {
            fridayPlus = 5 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, fridayPlus);
        Date friday = currentDate.getTime();

        return friday;

    }

    /**
     * 将日期格式为YYYYMMDD的转换为YYYY-MM-DD
     * 
     * @param date
     *            YYYYMMDD
     * @return
     */
    public static String String8ToDateStr(String date) {
        if (date == null) {
            return null;
        }
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        StringBuilder sb = new StringBuilder();
        sb.append(year).append("-").append(month).append("-").append(day);
        return sb.toString();
    }

    /**
     * 时间差
     * 
     * @param beginDate
     * @param endDate
     * @param returnType
     *            参考DIFFER_IN_xxx
     * @return
     */
    public static long differDate(Date beginDate, Date endDate, int returnType) {
        long begin = beginDate.getTime(), end = endDate.getTime();
        long delta = end - begin;
        long ret = 0;

        switch (returnType) {
        case DIFFER_IN_SECOND:
            ret = delta / 1000;
            break;
        case DIFFER_IN_MINUTE:
            ret = delta / 1000 / 60;
            break;
        case DIFFER_IN_HOUR:
            ret = delta / 1000 / 60 / 60;
            break;
        case DIFFER_IN_DAYS:
            ret = delta / 1000 / 60 / 60 / 24;
            break;
        default:
            break;
        }

        return ret;
    }
}
