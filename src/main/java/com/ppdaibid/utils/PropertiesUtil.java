package com.ppdaibid.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PropertiesUtil {
	private static final Logger logger = Logger.getLogger(Properties.class);

	private static final String DEFAULT_PROPERTIES = "conf.properties";
	private final static Properties properties;
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	private PropertiesUtil() {
//		properties = 
	}
	
	static {
		properties = initProperties(DEFAULT_PROPERTIES);
	}

	private static Properties initProperties(String propertiesFileName) {
		InputStream inputStream = null;
		Properties props = new Properties();

		try {
			Resource resource = resourceLoader.getResource(propertiesFileName);
			inputStream = resource.getInputStream();
			props.load(inputStream);
		} catch (Exception ex) {
			logger.error("初始化Properties文件异常", ex);
			ex.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("关闭inputStream异常", e);
			}
		}

		return props;
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	public static String getProperty(String key, String defaultValue) {
		String value = properties.getProperty(key);
		return (value != null) ? value.trim() : defaultValue;
	}
}
