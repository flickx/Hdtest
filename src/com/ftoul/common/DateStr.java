package com.ftoul.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取日期字符串
 * @author flick
 *
 */
public class DateStr {

	private Date date;
	private String format;
	
	/*public DateStr() {
		this.date = new Date();
		this.format = "yyyy-MM-dd HH:mm:ss";
	}*/
	public DateStr() {
		this.date = new Date();
		this.format = "yyyy-MM-dd HH:mm:ss";
	}
	
	/*public DateStr(Date date) {
		this.date = date;
		this.format = "yyyy-MM-dd HH:mm:ss";
	}*/
	
	public DateStr(Date date) {
		this.date = date;
		this.format = "yyyy-MM-dd HH:mm:ss";
	}
	
	public DateStr(String format) {
		this.date = new Date();
		this.format = format;
	}
	
	public DateStr(Date date, String format) {
		this.format = format;
		this.date = date;
	}
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sf=new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	public long compare(String arg1,String arg2) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = df.parse(arg1);
		Date date=df.parse(arg2);
		long l=now.getTime()-date.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long total = hour*60+min;
		return total;
	}
	
	public String getStartTime(){  
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    Date start = calendar.getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    String startTime = sdf.format(start);
	    return startTime;
    }  
      
	public String getEndTime(){  
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Date());
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.add(Calendar.DAY_OF_MONTH, 1);
	    calendar.add(Calendar.SECOND, -1);
	    Date end = calendar.getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    String endTime = sdf.format(end);
	    return endTime;
    }  
	public static void main(String[] args){
		System.out.println(new DateStr().getEndTime());
	}
}
