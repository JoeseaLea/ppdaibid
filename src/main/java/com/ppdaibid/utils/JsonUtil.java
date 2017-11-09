package com.ppdaibid.utils;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class JsonUtil {
	private static final Logger logger = Logger.getLogger(JsonUtil.class);
	public static String getString(JSONObject jsonObject, String key, String defaultValue) {
		try {
			jsonObject.get(key);
		} catch (Exception e) {
			logger.error("Json解析错误", e);
		}
		
		try {
			return jsonObject.getString(key);
		} catch (Exception e) { }
		
		return defaultValue;
	}
	
	public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
		try {
			jsonObject.get(key);
		} catch (Exception e) {
			logger.error("Json解析错误", e);
		}
		
		try {
			return jsonObject.getInt(key);
		} catch (Exception e) { }
		
		return defaultValue;
	}
	
	public static long getLong(JSONObject jsonObject, String key, long defaultValue) {
		try {
			jsonObject.get(key);
		} catch (Exception e) {
			logger.error("Json解析错误", e);
		}
		
		try {
			return jsonObject.getLong(key);
		} catch (Exception e) { }
		
		return defaultValue;
	}
	
	public static double getDouble(JSONObject jsonObject, String key, double defaultValue) {
		try {
			jsonObject.get(key);
		} catch (Exception e) {
			logger.error("Json解析错误", e);
		}
		
		try {
			return jsonObject.getDouble(key);
		} catch (Exception e) { }
		
		return defaultValue;
	}
	public static float getFloat(JSONObject jsonObject, String key, float defaultValue) {
		try {
			jsonObject.get(key);
		} catch (Exception e) {
			logger.error("Json解析错误", e);
		}
		
		try {
			return jsonObject.getFloat(key);
		} catch (Exception e) { }
		
		return defaultValue;
	}
	public static boolean getBoolean(JSONObject jsonObject, String key, boolean defaultValue) {
		try {
			jsonObject.get(key);
		} catch (Exception e) {
			logger.error("Json解析错误", e);
		}
		
		try {
			return jsonObject.getBoolean(key);
		} catch (Exception e) { }
		
		return defaultValue;
	}
}
