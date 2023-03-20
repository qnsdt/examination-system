package com.hig.utils;

import java.util.HashMap;
import java.util.Map;

import com.hig.dto.ResultPackage;

public class PackageWork {
	
	public static Map<String, Object> ToMap(int errcode,String errmsg,Object data)
	{
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("errcode", errcode);
		resultMap.put("errmsg", errmsg);
		resultMap.put("data", data);
		
		return resultMap;
	}
	
	public static Map<String, Object> Right(Object data)
	{
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("errcode", 0);
		resultMap.put("errmsg", "");
		resultMap.put("data", data);
		
		return resultMap;
	}

}
