package com.hig.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ResultPackage {
	private int errcode;
	private String errmsg;
	private Object data;
	
	public ResultPackage( ) {
	}

	public ResultPackage(int errcode, String errmsg, Object data) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.data = data;
	}
	
	public ResultPackage (Map<String, Object> objmap)
	{
		String errcode = objmap.get("errcode").toString();
				
	    if (errcode.indexOf(".") > 0)
	    	errcode = errcode.substring(0, errcode.indexOf("."));
		this.errcode = Integer.parseInt(errcode);
		this.errmsg = objmap.get("errmsg").toString();
		this.data = objmap.get("data");		
	}
	
	public ResultPackage Right(Object data)
	{
		return new ResultPackage(0,"",data);		
	}
	
	
	
}
