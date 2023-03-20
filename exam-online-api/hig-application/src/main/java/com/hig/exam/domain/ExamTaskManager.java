package com.hig.exam.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 创建考试对象 exam_task_manager
 * 
 * @author qnsdt
 * @date 2023-01-06
 */
public class ExamTaskManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试序号 */
    @Excel(name = "考试序号")
    private Long examId;

    /** 考试代码 */
    @Excel(name = "考试代码")
    private String examCode;

    /** 考试名称 */
    @Excel(name = "考试名称")
    private String examName;

    /** 考试说明 */
    @Excel(name = "考试说明")
    private String examDescribe;

    /** 组卷方式 */
    @Excel(name = "组卷方式")
    private String buildType;

    /** 强制抽卷 */
    @Excel(name = "强制抽卷")
    private String forceDone;

    /** 考试题库 */
    @Excel(name = "考试题库")
    private String examBank;

    /** 考试题库文字 */
    @Excel(name = "考试题库文字")
    private String examBankText;


    /** 图片链接 */
    @Excel(name = "图片链接")
    private String pictureUrl;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    
	/** 考试时长 */
    @Excel(name = "考试时长")
    private String examDuration;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    private String startDateText;
    
    public String getStartDateText() {
		return startDateText;
	}

	public void setStartDateText(String startDateText) {
		this.startDateText = startDateText;
	}


	private String endDateText;
	
	public String getEndDateText() {
		return endDateText;
	}

	public void setEndDateText(String endDateText) {
		this.endDateText = endDateText;
	}

    /** 创建部门 */
    private Long createDept;

    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
    }
    public void setExamCode(String examCode) 
    {
        this.examCode = examCode;
    }

    public String getExamCode() 
    {
        return examCode;
    }
    public void setExamName(String examName) 
    {
        this.examName = examName;
    }

    public String getExamName() 
    {
        return examName;
    }
    public void setExamDescribe(String examDescribe) 
    {
        this.examDescribe = examDescribe;
    }

    public String getExamDescribe() 
    {
        return examDescribe;
    }
    public void setBuildType(String buildType) 
    {
        this.buildType = buildType;
    }

    public String getBuildType() 
    {
        return buildType;
    }
    public void setForceDone(String forceDone) 
    {
        this.forceDone = forceDone;
    }

    public String getForceDone() 
    {
        return forceDone;
    }
    public void setExamBank(String examBank) 
    {
        this.examBank = examBank;
    }

    public String getExamBank() 
    {
        return examBank;
    }
    public void setPictureUrl(String pictureUrl) 
    {
        this.pictureUrl = pictureUrl;
    }

    public void setExamBankText(String examBankText) 
    {
        this.examBankText = examBankText;
    }

    public String getExamBankText() 
    {
        return examBankText;
    }
    
    public String getPictureUrl() 
    {
        return pictureUrl;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setExamDuration(String examDuration) 
    {
        this.examDuration = examDuration;
    }

    public String getExamDuration() 
    {
        return examDuration;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setCreateDept(Long createDept) 
    {
        this.createDept = createDept;
    }

    public Long getCreateDept() 
    {
        return createDept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examId", getExamId())
            .append("examCode", getExamCode())
            .append("examName", getExamName())
            .append("examDescribe", getExamDescribe())
            .append("buildType", getBuildType())
            .append("forceDone", getForceDone())
            .append("examBank", getExamBank())
            .append("examBankText", getExamBankText())
            .append("pictureUrl", getPictureUrl())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("examDuration", getExamDuration())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createDept", getCreateDept())
            .append("createTime", getCreateTime())
            .toString();
    }
}
