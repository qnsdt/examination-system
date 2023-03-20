package com.hig.cms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
 
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
    /**
     * 文件上传处理
     *
     * @param file
     * @return
     */
	public static String writeUploadFile(MultipartFile file,String path) {
        String filename = file.getOriginalFilename();
        System.out.println("原始文件名:" + filename);
        File fileDir = new File(path);
        if (!fileDir.exists())
            fileDir.mkdirs();
 
        String extname = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "NOT_IMAGE";
        }
         
        filename = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString( 4 ) + "." + extname;
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(path + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        	if (input != null) {
        		try {
        			input.close();
                } catch (IOException e) {
                	e.printStackTrace();
                    return null;
                }
        	}
        	
        	if (fos != null) {
        		try {
        			fos.close();
                } catch (IOException e) {
                	e.printStackTrace();
                    return null;
                }
        	}
        }
        return filename;
    }
}
