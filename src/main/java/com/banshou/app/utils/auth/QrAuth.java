package com.banshou.app.utils.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.banshou.app.utils.common.Constants;
import com.banshou.app.utils.common.DBConn;

public class QrAuth {

	public static String addCode(String authCode, String status) {
		DBConn conn = new DBConn();
		int result = conn.execOther(Constants.ADDQRCODE, new Object[] { authCode, status });
		return (result > 0) ? "success" : "error";
	}

	public static String reload(String code) {
		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(Constants.RELOADQRSTATUS, new Object[] { code });
		String status = "";
		try {
			while (rs.next()) {
				status = rs.getString("qr_status");
			}
		} catch (SQLException e) {
			status = "error";
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static String expired(String code) {
		DBConn conn = new DBConn();
		int result = conn.execOther(Constants.UPDATEQRCODE, new Object[] { "expired", code });
		return (result > 0) ? "success" : "fail";
	}
	
	public static String loadOpenid(String code) {
		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(Constants.LOADOPENID, new Object[] { code });
		String status = "";
		try {
			while (rs.next()) {
				status = rs.getString("qr_openid");
			}
		} catch (SQLException e) {
			status = "error";
			e.printStackTrace();
		}
		return status;
	}
	
	public static void main(String[] args) {
		QrAuth.reload("QRAUTH20150406112842655127372");
	}
}
