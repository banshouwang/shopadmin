package com.banshou.app.utils.common;

public class GetServerDir {

	private GetServerDir() {

	}

	public static GetServerDir getInstance() {
		return new GetServerDir();
	}

	public String getRootPath() {
		String result = GetServerDir.class.getResource("GetServerDir.class")
				.toString();
		int index = result.indexOf("WEB-INF");
		if (index == -1) {
			index = result.indexOf("bin");
		}
		result = result.substring(0, index);
		if (result.startsWith("jar")) {
			result = result.substring(10);
		} else if (result.startsWith("file")) {
			result = result.substring(6);
		}
		return result;
	}

}
