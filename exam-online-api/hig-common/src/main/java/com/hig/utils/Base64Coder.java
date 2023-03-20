package com.hig.utils;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Coder {
	public  static String Encode(byte[] bytes)
	{
		BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
	}
	
	public static byte[] Decode(String basestr) throws Exception
	{
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes=null;
        try {
			bytes =  decoder.decodeBuffer(basestr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
        
        return bytes;

	}
}
