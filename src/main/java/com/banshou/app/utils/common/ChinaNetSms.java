package com.banshou.app.utils.common;
//package com.banshou.utils.common;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//public class ChinaNetSms {
//	public void sendSms() {
//		HttpClient httpclient = new DefaultHttpClient();
//		Date date = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		String timestamp = dateFormat.format(date);
//		HttpPost get = new HttpPost("http://api.189.cn/v2/dm/randcode/sendSms?app_id=115034730000039878&access_token=fdbcbe71de64720c8165f20a7f3dcf811423294743894&timestamp=2015-02-07 16:25:26&token=522ec18b-c599-4018-8775-f3fe077c0f9d&phone=18501611079&randcode=123456&sign=lxnV7jmFPltEu4VFNkNpex5OwF4%3D");
//		try {
//			HttpResponse response = httpclient.execute(get);
//
//			HttpEntity entity = response.getEntity();
//			InputStream instreams = entity.getContent();
//			String str = convertStreamToString(instreams);
//			System.out.println("Do something");
//			System.out.println(str);
//		} catch (Exception e) {
//
//		}
//		/*
//		 * HttpPost httppost = new HttpPost(
//		 * "http://api.189.cn/v2/dm/randcode/sendSms?app_id=115034730000039878&access_token=50868ce8d55c63bac91a909c7ebd7e23&token=ccc&phone=18501611079&randcode=123465&timestamp=fff&sign=ggg"
//		 * ); try { HttpResponse response = httpclient.execute(httppost);
//		 * HttpEntity entity = response.getEntity(); if (entity != null) {
//		 * InputStream instreams = entity.getContent(); String str =
//		 * convertStreamToString(instreams); System.out.println("Do something");
//		 * System.out.println(str); httppost.abort(); } } catch (Exception e) {
//		 * 
//		 * }
//		 */
//	}
//
//	public static String convertStreamToString(InputStream is) {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		StringBuilder sb = new StringBuilder();
//
//		String line = null;
//		try {
//			while ((line = reader.readLine()) != null) {
//				sb.append(line + "\n");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return sb.toString();
//	}
//}
