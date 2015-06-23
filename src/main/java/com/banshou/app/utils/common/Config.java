package com.banshou.app.utils.common;

import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties prop = new Properties();
	static {
		try {
			prop.load(Config.class.getResourceAsStream("/properties/info.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 设置常量
	public static final String CLASS_NAME = prop.getProperty("CLASS_NAME");
	public static final String URL = prop.getProperty("URL");
	public static final String USERNAME = prop.getProperty("USERNAME");
	public static final String PASSWORD = prop.getProperty("PASSWORD");
	public static final String ALIKEYID = prop.getProperty("ALIKEYID");
	public static final String ALIKEYSCR = prop.getProperty("ALIKEYSCR");
	public static final String ALIURL = prop.getProperty("ALIURL");

	public static final String HOSTNAME = prop.getProperty("HOSTNAME");
	public static final String APPID = prop.getProperty("APPID");
	public static final String APPSECRET = prop.getProperty("APPSECRET");

	// Send SMS
	public static final String ACCOUNDSID = prop.getProperty("ACCOUNDSID");
	public static final String SMSTOKEN = prop.getProperty("SMSTOKEN");
	public static final String SMSAPPID = prop.getProperty("SMSAPPID");
	public static final String SMSTMPID = prop.getProperty("SMSTMPID");
	public static final String VALIDTIME = prop.getProperty("VALIDTIME");
	public static final String SMSCONSUMETMPID = prop.getProperty("SMSCONSUMETMPID");
	public static final String CBSPHONE = prop.getProperty("CBSPHONE");
	
	public static final String BUCKETNAME = prop.getProperty("BUCKETNAME");
	public static final String GOODSKEY = prop.getProperty("GOODSKEY");
	public static final String STOREKEY = prop.getProperty("STOREKEY");
	public static final String STOREICONKEY = prop.getProperty("STOREICONKEY");
}
