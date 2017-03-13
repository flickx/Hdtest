package com.ftoul.common;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 日期时间及字符串转换相关工具类
 * @author flick
 *
 */
@Component
public class DateUtil {
	/**
     * 日期转为字符串
     * @param d 日期类型
     * @param s 转换格式如"yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
	public static String dateFormatToString(Date d,String s){
		SimpleDateFormat sf=new SimpleDateFormat(s);
		return sf.format(d);
	}
	/**
	 * 字符串转换为日期
	 * @param str 字符串如：2012-12-01
	 * @param format 字符串对应日期格式如：yyyy-MM-dd
	 * @return 日期Date
	 */
	public static Date stringFormatToDate(String str,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 数字格式化转换为字符串
	 * @param num 数字如：12
	 * @param format 格式如：##########0.00
	 * @return 返回12.00
	 */
	public static String numberToStr(Object num,String format){
	    String res = "";
	    if(num instanceof Double){
	        DecimalFormat df=new DecimalFormat(format);
	        res=df.format(num);
	    }
	    return res;
	}
	/**
	 * 日期加减天数
	 * @param Date 数字如：new date()
	 * @param num 格式如：1
	 * @return 返回date2
	 */
	public static Date dateDay(Date day,int num){
		Date d2 = new Date(day.getTime()+(long)24*60*60*1000*num);
	    return d2;
	}
	/**
	 * 两个日期加减
	 * @param day1 数字如：d1
	 * @param day2 格式如：d2
	 * @return 返回12
	 */
	public static Integer dateToDate(Date day1,Date day2){
		Integer i = (int)Math.ceil(((day2.getTime()-day1.getTime())/(1000*60*60*24)));
	    return i;
	}
	/**
	 * 根据年份跟月份返回当月天数，包含闰年计算
	 * @param year 如2012
	 * @param month 月12
	 * @return 天数31
	 */
	public static int dayNum(String year,String month){
		int day=0;
		if("01".equals(month)){
			day=31;
		}
		else if("02".equals(month)){
			int yearNum = Integer.valueOf(year);
			if(yearNum%4!=0){
				day=28;
			}else if(yearNum%100==0&&yearNum%4!=0){
				day=28;
			}else{
				day=29;
			}
		}
		else if("03".equals(month)){
			day=31;
		}
		else if("04".equals(month)){
			day=30;
		}
		else if("05".equals(month)){
			day=31;
		}
		else if("06".equals(month)){
			day=30;
		}
		else if("07".equals(month)){
			day=31;
		}
		else if("08".equals(month)){
			day=31;
		}
		else if("09".equals(month)){
			day=30;
		}
		else if("10".equals(month)){
			day=31;
		}
		else if("11".equals(month)){
			day=30;
		}
		else if("12".equals(month)){
			day=31;
		}
		return day;
	}
	/**
	 * 根据月份返回当前季度最后一个月
	 * @param month 月12
	 * @return 季度最后一个月
	 */
	public static int monthJDEnd(int month){
		if(month<=2){
			return 2;
		}else if(month<=5){
			return 5;
		}else if(month<=8){
			return 8;
		}else{
			return 11;
		}
	}
	/**
	 * 根据月份返回当前季度
	 * @param month 月12
	 * @return 季度
	 */
	public static int monthJD(int month){
		if(month<=3){
			return 1;
		}else if(month<=6){
			return 2;
		}else if(month<=9){
			return 3;
		}else{
			return 4;
		}
	}
}
