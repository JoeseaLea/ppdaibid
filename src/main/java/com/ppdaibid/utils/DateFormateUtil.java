package com.ppdaibid.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateFormateUtil {
	
	private static final Logger logger = Logger.getLogger(DateFormateUtil.class);
	
	public static Date formateDate(String dateStr) {
		if (null == dateStr || "".equals(dateStr)) {
			return null;
		}
		
		dateStr = dateStr.replaceAll("T", " ");
		
		Date date = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			date = df.parse(dateStr);
			return date;
		} catch (ParseException e) { }
		
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df.parse(dateStr);
			return date;
		} catch (ParseException e) { }
		
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			date = df.parse(dateStr);
			return date;
		} catch (ParseException e) { }
		
		df = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			date = df.parse(dateStr);
			return date;
		} catch (ParseException e) { }
		
		df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(dateStr);
			return date;
		} catch (ParseException e) { }
		
		return date;
	}
	
	public static String formateDate(Date date) {
		if (null == date) {
			return null;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateStr = null;
		try {
			dateStr = df.format(date);
		} catch (Exception e) {
			logger.error("日期格式化错误（" + date.toString() + "）：", e);
		}
		return dateStr;
	}
}
