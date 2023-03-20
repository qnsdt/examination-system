package com.hig.ueditor.controller;


import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hig.ueditor.ActionEnter;
import com.hig.ueditor.ConfigManager;
import com.hig.ueditor.common.UEditorFile;
import com.hig.ueditor.common.UEditorUpload;
import com.hig.ueditor.define.ActionMap;
import com.hig.ueditor.define.State;


@RestController
@RequestMapping("/ueditor")
@CrossOrigin(allowCredentials = "true")
public class UEditorController {
	@Autowired
	private UEditorUpload uEditorUpload;
	
    
	@RequestMapping("/test")
	public String getTest() {
		return "ok";
	}
    
	
	@RequestMapping("/config")
    public String exec(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "action") String action,
                       @RequestParam(value = "upfile", required = false) MultipartFile upfile) throws Exception {
        if (action.equals("config")) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            return new ActionEnter(request, rootPath).exec();
        } else if (action.equals("uploadimage")) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");
    		String contextPath = request.getContextPath();
    		ConfigManager configManager = ConfigManager.getInstance( rootPath, contextPath, request.getRequestURI() );
        	Map<String, Object> conf = new HashMap<String, Object>();
        	conf = configManager.getConfig(ActionMap.UPLOAD_IMAGE);
        	
        	
        	State uploadState = uEditorUpload.uploadImage(request, conf);
        	
            return uploadState.toJSONString();
        }  else if (action.equals("uploadvideo")) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");
    		String contextPath = request.getContextPath();
    		ConfigManager configManager = ConfigManager.getInstance( rootPath, contextPath, request.getRequestURI() );
        	Map<String, Object> conf = new HashMap<String, Object>();
        	conf = configManager.getConfig(ActionMap.UPLOAD_VIDEO);
        	
        	
        	State uploadState = uEditorUpload.uploadMovie(request, conf);
        	
            return uploadState.toJSONString();
        	
        } else if (action.equals("configmanager")) {
        
        	
        	return "Done|";
        }
        
        return "无效Action";
    }
	
}
