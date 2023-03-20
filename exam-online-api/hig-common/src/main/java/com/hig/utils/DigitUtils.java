package com.hig.utils;

public class DigitUtils {
	
	public static String toChinese(String str) {
        //数字对应的汉字
        String[] num = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        //单位
        String[] unit = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千", "万亿"};
        //将输入数字转换为字符串
        //将该字符串分割为数组存放
        char[] ch = str.toCharArray();
        //结果 字符串
        String result = "";
        int length = ch.length;
        for (int i = 0; i < length; i++) {
            int c = (int) ch[i] - 48;
            if (c != 0) {
                result += num[c - 1] + unit[length - i - 1];
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
	    
	    // 将字符串数字转化为汉字
		
		toChinese("30");
	}   
}
