package com.banshou.app.utils.common;

import java.util.Random;
import java.util.UUID;

public class CodeGenerator {

	public static String mobileValid() {
		Random rd = new Random();
		String code = "";
		int getNum;
		do {
			getNum = Math.abs(rd.nextInt()) % 10 + 48;
			char num = (char) getNum;
			String tmp = Character.toString(num);
			code += tmp;
		} while (code.length() < 6);
		System.out.println(code);
		return code;
	}
	
	public static String UUIDGenerator(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
