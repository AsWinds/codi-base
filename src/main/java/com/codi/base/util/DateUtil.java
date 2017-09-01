package  com.codi.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * 功能描述：实现Date与String的转换
 *
 *
 */
public class DateUtil
{
  public static String defaultDateTimeFormatString = "yyyy-MM-dd HH:mm:ss";
  public static String defaultDateFormatString = "yyyy-MM-dd";
  public static String YYYYMMDD = "yyyy-MM-dd";

  /**
   * 
   * 功能描述：String转换成Date,使用默认匹配模式
   *
   * @author  王明山(wangmingshan)
   * <p>创建日期 ：2011-12-28 下午4:46:10</p>
   *
   * @param source
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static Date parse(String source)
  {
    try
    {
      SimpleDateFormat format = new SimpleDateFormat();
      if (source.indexOf(":") > 0)
        format.applyPattern(defaultDateTimeFormatString);
      else {
        format.applyPattern(defaultDateFormatString);
      }
      return format.parse(source.trim());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 
   * 功能描述：String转换成Date,可以指定匹配模式
   *
   * @author  王明山(wangmingshan)
   * <p>创建日期 ：2011-12-28 下午4:46:32</p>
   *
   * @param source
   * @param formatString
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static Date parse(String source, String formatString)
  {
    try
    {
      SimpleDateFormat format = new SimpleDateFormat();
      format.applyPattern(formatString.trim());
      return format.parse(source.trim());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * 
   * 功能描述：Date转换成String,使用默认匹配模式
   *
   *
   * @param date
   * @return
   *
   */
  public static String format(Date date)
  {
    SimpleDateFormat format = new SimpleDateFormat(defaultDateTimeFormatString);
    return format.format(date);
  }
  /**
   * 
   * 功能描述：Date转换成String,可以指定匹配模式
   *
   *
   * @param date
   * @param FormatString
   * @return
   *
   */
  public static String format(Date date, String FormatString)
  {
    SimpleDateFormat format = new SimpleDateFormat(FormatString);
    return format.format(date);
  }
  
  /***************************************************************************
	 * dateStrToLong
	 * 
	 * @param dateStr
	 * @return long theDay
	 **************************************************************************/
  public static long dateStrToLong(String dateStr) {
		long theDate = System.currentTimeMillis();
		Date thisDate = stringToDate(dateStr);
		if (thisDate != null) {
			theDate = thisDate.getTime();
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
		Date theDate = null;
		try {
			if (s != null) {
				SimpleDateFormat dateFormatter = new SimpleDateFormat(
						"yyyy-MM-dd");
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
	
	
	public static String currPK(){
		Date d =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String d1 =sdf.format(d);

		try {
			Thread.sleep(50);/*沉睡50毫秒*/
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		return d1;
		
	}

}