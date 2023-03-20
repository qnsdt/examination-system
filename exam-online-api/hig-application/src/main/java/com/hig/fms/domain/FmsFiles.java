package com.hig.fms.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import lombok.Data;

@Data
public class FmsFiles {
    /** 文件序号 */
    private int fileId;

    /** 文件标题 */
    @Excel(name = "文件标题")
    private String fileTitle;

    /** 文件描述 */
    @Excel(name = "文件描述")
    private String fileDescribe;

    /** 文件名称 */
    private String fileName;

    /** 文件路径 */
    private String filePath;

    /** 文件链接 */
    private String fileUrl;

    /** 文件类型 */
    private String fileSuffix;

    /** 原始名称 */
    private String originalName;

    /** 上传者 */
    @Excel(name = "上传者")
    private String uploadName;

    /** 上传部门 */
    private String uploadDept;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 状态 */
    private int status;
    
	public FmsFiles() {
		super();
	}
    
    public FmsFiles(String fileTitle, String fileDescribe, String fileName, String filePath, String fileUrl,
			String fileSuffix, String originalName, String uploadName) {
		super();
		this.fileTitle = fileTitle;
		this.fileDescribe = fileDescribe;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileUrl = fileUrl;
		this.fileSuffix = fileSuffix;
		this.originalName = originalName;
		this.uploadName = uploadName;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("fileId", getFileId())
            .append("fileTitle", getFileTitle())
            .append("fileDescribe", getFileDescribe())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileUrl", getFileUrl())
            .append("fileSuffix", getFileSuffix())
            .append("originalName", getOriginalName())
            .append("uploadName", getUploadName())
            .append("uploadDept", getUploadDept())
            .append("uploadTime", getUploadTime())
            .append("status", getStatus())
            .toString();
    }




}
