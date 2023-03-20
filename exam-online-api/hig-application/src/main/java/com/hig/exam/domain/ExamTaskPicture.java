package com.hig.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试任务图片管理对象 exam_task_picture
 * 
 * @author qnsdt
 * @date 2023-01-18
 */
public class ExamTaskPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片序号 */
    private Long photoId;

    /** 图片代码 */
    private String photoCode;

    /** 文档代码 */
    @Excel(name = "文档代码")
    private String photoPath;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String photoUrl;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 原始名称 */
    @Excel(name = "原始名称")
    private String originalName;

    private String createBy;
    public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

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
    public void setPhotoCode(String photoCode) 
    {
        this.photoCode = photoCode;
    }

    public String getPhotoCode() 
    {
        return photoCode;
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
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setOriginalName(String originalName) 
    {
        this.originalName = originalName;
    }

    public String getOriginalName() 
    {
        return originalName;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    
    

	public ExamTaskPicture(String photoCode, String photoPath, String photoUrl, String fileName, String originalName,
			String createBy) {
		super();
		this.photoCode = photoCode;
		this.photoPath = photoPath;
		this.photoUrl = photoUrl;
		this.fileName = fileName;
		this.originalName = originalName;
		this.createBy = createBy;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("photoCode", getPhotoCode())
            .append("photoPath", getPhotoPath())
            .append("photoUrl", getPhotoUrl())
            .append("fileName", getFileName())
            .append("originalName", getOriginalName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
