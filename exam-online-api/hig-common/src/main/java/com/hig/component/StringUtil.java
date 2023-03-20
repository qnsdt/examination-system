package com.hig.component;

public class StringUtil {

	public static String getFileName(String text) throws Exception {
		int start = text.indexOf("\"");
		
		int end = text.lastIndexOf("\"");
		
		String result = null;
		if (end > start) {
			result = text.substring(start + 1,end);
		}
		else {
			throw new Exception("不是有效的Http头");
		}
		
		return result;
	}
}
