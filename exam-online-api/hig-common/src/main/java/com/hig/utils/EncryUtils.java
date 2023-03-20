package com.hig.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class EncryUtils {

	private static String password = "Z1cZbNeiq75+w==3Y1p2ioY";

	public static String EncryText(String text) {
		StandardPBEStringEncryptor encryptOr = new StandardPBEStringEncryptor();
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword(password);
		// 应用配置
		encryptOr.setConfig(config);
		
		return encryptOr.encrypt(text);		
	}
	
	public static String DecryText(String entext) {
		if (entext == null || entext.equals("")) return null;
		
		StandardPBEStringEncryptor encryptOr = new StandardPBEStringEncryptor();
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword(password);
		// 应用配置
		encryptOr.setConfig(config);
		
		return encryptOr.decrypt(entext);		
		
	}
}
