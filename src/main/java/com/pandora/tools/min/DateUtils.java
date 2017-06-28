package com.pandora.tools.min;

/**
 * 一个日期的工具类 
 * @author C.H.
 * @CreateTime 2016年3月12日 上午2:45:29
 */
public class DateUtils {

	public static final String FORMAT_DATE_DAY = "yyyy-mm-dd";
	public static final String FORMAT_DATE_SECOND = "yyyy-mm-dd hh:MM:ss";
	public static final String FORMAT_DATE_SECOND_CN = "yyyy年mm月dd hh:MM:ss";
	public static final String FORMAT_DATE_MILLISECOND = "yyyy-mm-dd hh:MM:ss:SS";
	
	public static final String FORMAT_DATE_INTEGER_DAY = "yyyymmdd";
	public static final String FORMAT_DATE_INTEGER_SECOND = "yyyymmddhhMMss";

	public static String printConsuming(Long time){
		time = System.currentTimeMillis() - time;
		long sss = time % 1000;
		time = time/1000;
		long second = time % 60;
		time = time/60;
		long minute = time % 60;
		long hours = time /60;
		return (hours== 0 ? "" : hours + "小时" ) + minute + "分" + second + "秒" + sss + "毫秒";
	}
}
