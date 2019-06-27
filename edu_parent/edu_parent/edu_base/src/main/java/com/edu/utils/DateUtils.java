package com.edu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	// 处理时间格式的字符串数组 0 1
	// 2 3 4 5 6 7
	public final static String[] dateParsePatterns = { "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyyMMddHHmmss.SSS", "yyyyMMddHHmmss", "yyyyMMddHHmm",
			"yyyyMMdd" };
	
	/**
	 * 获取当天0点的时间
	 * 
	 * @return
	 */
	public static Long getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	/**
	 * 获取明天0点的时间
	 * 
	 * @return
	 */
	public static Long getTimestomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得本周一0点时间
	 * 
	 * @return
	 */
	public static Long getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得本月第一天0点时间
	 * 
	 * @return
	 */
	public static Long getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTimeInMillis();
	}

	/**
	 * 获得本年第一天0点时间
	 * 
	 * @return
	 */
	public static Long getTimesYearmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		return cal.getTimeInMillis();
	}
	/**
	 * 获取今天yyyyMMdd格式字符串
	 * @return
	 */
	public static String getNowShort(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(new Date());
	}

	public static Date setTimeStrToShortDate(String timeStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date setTimeStrToLongDate(String timeStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date setTimeStr(String timeStr,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDateFormat(Date date, String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	public static Long dealTimestamp(Long timetamp) {
		if(timetamp!=null && timetamp>0 && timetamp < 1000000000000l ) {
			timetamp = timetamp*10;
			timetamp =dealTimestamp(timetamp);
		}
		return timetamp;
	}
	
	public static Long subPhpTimestamp(Date date) {
		return date.getTime()/1000l;
	}
	
	
}
