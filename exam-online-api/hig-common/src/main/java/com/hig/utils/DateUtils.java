package com.hig.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String toString(Date date)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
		
		
	}
	
	public static String toMonthDayString(Date date)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("MMdd");
		return dateformat.format(date);
		
		
	}
	
	public static String toYearString(Date date)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		return dateformat.format(date);
				
	}
	
	public static String toDateString()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		return dateformat.format(new Date());
		
		
	}
	
	public static String toShortDateString(Date date)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(date);
		
		
	}
	
	public static String toMonthDayString()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("MMdd");
		return dateformat.format(new Date());
		
		
	}
	
	public static String toYearString()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		return dateformat.format(new Date());
				
	}
}
