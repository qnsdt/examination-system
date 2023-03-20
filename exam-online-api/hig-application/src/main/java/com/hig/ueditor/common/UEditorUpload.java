package com.hig.ueditor.common;

import com.hig.ueditor.PathFormat;
import com.hig.ueditor.define.AppInfo;
import com.hig.ueditor.define.BaseState;
import com.hig.ueditor.define.FileType;
import com.hig.ueditor.define.State;
import com.hig.ueditor.upload.StorageManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class UEditorUpload {
    public final State uploadImage(HttpServletRequest request, Map<String, Object> conf) throws IOException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}
 
 
		try {
 
			/*========== 添加的内容 ==========*/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
			if (multipartFile == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			// 添加结束
 
			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename(); //　修改的
			String suffix = FileType.getSuffixByFilename(originFileName);  // 此处取文件扩展名
 
			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
 
			long maxSize = ((Long) conf.get("maxSize")).longValue();
 
			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
 
			savePath = PathFormat.parse(savePath, "");
			
			savePath += suffix;
			String physicalPath = (String) conf.get("basePath") + savePath;
			InputStream is = multipartFile.getInputStream();
			State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);
			is.close();
 
			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}
 
			return storageState;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);

    }
    
    public final State uploadMovie(HttpServletRequest request, Map<String, Object> conf) throws IOException {

 
		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}
 
 
		try {
 
			/*========== 添加的内容 ==========*/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
			if (multipartFile == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			// 添加结束
 
			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename(); //　修改的
			String suffix = FileType.getSuffixByFilename(originFileName);  // 此处取文件扩展名
 
			// 这里重新生成文件名

			originFileName = "movie";
 
			long maxSize = ((Long) conf.get("maxSize")).longValue();
 
			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
 
			savePath = PathFormat.parse(savePath, "");
			
			savePath += suffix;
			String physicalPath = (String) conf.get("basePath") + savePath;
			InputStream is = multipartFile.getInputStream();
			State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);
			is.close();
 
			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}
 
			return storageState;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);

    }
    
    private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);
 
		return list.contains(type);
	}

}
