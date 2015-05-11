package com.banshou.app.utils.common;

import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties prop = new Properties();	
	static{		
		try {
			//加载odb.properties配置文件
			prop.load(Config.class.getResourceAsStream("/info.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//设置常量
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
}
