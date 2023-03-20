package com.hig.fms.domain.dto;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class StdOut {
    public static final int SUCCESS = 200;
    public static final int FAIL = 400;
    public static final int PARAMETER_NULL = 500;
    public static final int NO_LOGIN = 600;
    private int code = 200;
    private Object model = null;
    private String message = null;
 
    public StdOut() {
        this.setCode(200);
        this.setModel((Object)null);
    }
 
    public StdOut(int code) {
        this.setCode(code);
        this.setModel((Object)null);
    }
 
    public StdOut(List<Map<String, Object>> model) {
        this.setCode(200);
        this.setModel(model);
    }
 
    public StdOut(int code, List<Map<String, Object>> model) {
        this.setCode(code);
        this.setModel(model);
    }
 
    public int getCode() {
        return this.code;
    }
 
    public void setCode(int code) {
        this.code = code;
    }
 
    public String toString() {
        return JSON.toJSONString(this);
    }
 
    public Object getModel() {
        return this.model;
    }
 
    public void setModel(Object model) {
        this.model = model;
    }
 
    public String getMessage() {
        return this.message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }


}
