package com.hig.fms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图片管理对象 fms_photo
 * 
 * @author qnsdt
 * @date 2021-12-14
 */
public class FmsPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片序号 */
    @Excel(name = "图片序号")
    private Long photoId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String photoPath;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String photoUrl;

    /** 原始名称 */
    @Excel(name = "原始名称")
    private String originalName;

    /** 上传者 */
    @Excel(name = "上传者")
    private String uploadName;

    /** 上传部门 */
    @Excel(name = "上传部门")
    private String uploadDept;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    public void setPhotoId(Long photoId) 
    {
        this.photoId = photoId;
    }

    public Long getPhotoId() 
    {
        return photoId;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setPhotoPath(String photoPath) 
    {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() 
    {
        return photoPath;
    }
    public void setPhotoUrl(String photoUrl) 
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() 
    {
        return photoUrl;
    }
    public void setOriginalName(String originalName) 
    {
        this.originalName = originalName;
    }

    public String getOriginalName() 
    {
        return originalName;
    }
    public void setUploadName(String uploadName) 
    {
        this.uploadName = uploadName;
    }

    public String getUploadName() 
    {
        return uploadName;
    }
    public void setUploadDept(String uploadDept) 
    {
        this.uploadDept = uploadDept;
    }

    public String getUploadDept() 
    {
        return uploadDept;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    
    public FmsPhoto(String fileName, String photoPath, String photoUrl, String originalName, String uploadName,
			String uploadDept, Long status) {
		super();
		this.fileName = fileName;
		this.photoPath = photoPath;
		this.photoUrl = photoUrl;
		this.originalName = originalName;
		this.uploadName = uploadName;
		this.uploadDept = uploadDept;
		this.status = status;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("fileName", getFileName())
            .append("photoPath", getPhotoPath())
            .append("photoUrl", getPhotoUrl())
            .append("originalName", getOriginalName())
            .append("uploadName", getUploadName())
            .append("uploadDept", getUploadDept())
            .append("uploadTime", getUploadTime())
            .append("status", getStatus())
            .toString();
    }
}
