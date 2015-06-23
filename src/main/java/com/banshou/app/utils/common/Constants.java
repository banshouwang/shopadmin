package com.banshou.app.utils.common;

public class Constants {
	
	// Constants that will be modified when deploy to PROD
	public static final String WXAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Config.APPID + "&redirect_uri=http%3A%2F%2F" + Config.HOSTNAME + "%2Fchebangshou%2Fmobile%2FauthQr.action&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	// Aliyun constants
	public static String BUCKETNAME = Config.BUCKETNAME;
	public static String GOODSKEY = Config.GOODSKEY;
	public static String STOREKEY = Config.STOREKEY;
	public static String STOREICONKEY = Config.STOREICONKEY;
	
	public static String ADDUSERSQL = "INSERT INTO bs_user (u_number, u_openid, u_credit, u_is_vip, u_mobile, u_password, u_is_shop, u_registry_time, u_point) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static String CHECKMOBILE = "SELECT u_mobile FROM bs_user WHERE u_mobile = ?";
	public static String CHECKOPENID = "SELECT u_openid FROM bs_user WHERE u_openid = ?";
	public static String ADDQRCODE = "INSERT INTO bs_qr_auth (qr_code, qr_status) VALUES (?, ?)";
	public static String UPDATEQRCODE = "UPDATE bs_qr_auth SET qr_status = ? WHERE qr_code = ?";
	public static String RELOADQRSTATUS = "SELECT qr_status FROM bs_qr_auth WHERE qr_code = ?";
	public static String LOADOPENID = "SELECT qr_openid FROM bs_qr_auth WHERE qr_code = ?";
	
	public static final String ADDPOINT = "UPDATE bs_user set u_point = ? WHERE u_openid = ?";

	
	// Goods related SQL
	public static String ADDGOODSSQL = "INSERT INTO bs_goods (g_number, g_name, g_category, g_is_valid, g_priceori, g_pricehere, g_is_ticket, g_ticket) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
}
