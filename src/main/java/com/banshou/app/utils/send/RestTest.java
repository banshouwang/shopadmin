package com.banshou.app.utils.send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestTest {

	public static String sendSms(String accountSid, String authToken, String appId, String templateId, String to, String param) {
		JsonReqClient client = new JsonReqClient();
		try {
			String result = client.templateSMS(accountSid, authToken, appId, templateId, to, param);
			System.out.println("Response content is: " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("请输入参数，以空格隔开...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String param = br.readLine();
		String[] params = param.split(" ");
		String method = params[0];
		if (method.equals("11")) { // 短信验证码
			String accountSid = "c3580391e343ee777a007365ad711a9d";
			String token = "a0dbfe0596be8555911c8ace36e73a90";
			String appId = "83f51a8c24b24a0cae65b6123f2d4360";
			String templateId = "1";
			String to = "18501611079";
			String para = "666666";
			sendSms(accountSid, token, appId, templateId, to, para);
		}
	}
}
