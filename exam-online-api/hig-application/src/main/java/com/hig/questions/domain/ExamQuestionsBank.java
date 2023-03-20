package com.hig.questions.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题库管理对象 exam_questions_bank
 * 
 * @author qnsdt
 * @date 2022-12-19
 */
public class ExamQuestionsBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 题库id */
    @Excel(name = "题库id")
    private Long bankId;

    /** 题库代码 */
    private String bankCode;

    /** 题库名称 */
    @Excel(name = "题库名称")
    private String bankName;

	/** 题库描述 */
    private String bankDescribe;

    /** 题库版本 */
    @Excel(name = "题库版本")
    private String bankVersion;

    /** 考试类型 */
    // @Excel(name = "考试类型")
    private Long examType;

    /** 考试类型 */
    @Excel(name = "考试类型")
    private String typeName;

   

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/** 上线日期 */
    private Date onlineDate;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String pictureUrl;

    /** 单选题数 */
    @Excel(name = "单选题数")
    private int radioNumber;


	/** 多选题数 */
    @Excel(name = "多选题数")
    private int choiceNumber;

    /** 判断题数 */
    @Excel(name = "判断题数")
    private int judgeNumber;

    /** 单选分数 */
    @Excel(name = "单选分数")
    private double radioScore;

    /** 多选分数 */
    @Excel(name = "多选分数")
    private double choiceScore;

    /** 判断分数 */
    @Excel(name = "判断分数")
    private double judgeScore;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 创建部门 */
    private Long createDept;

    public void setBankId(Long bankId) 
    {
        this.bankId = bankId;
    }

    public Long getBankId() 
    {
        return bankId;
    }
    public void setBankCode(String bankCode) 
    {
        this.bankCode = bankCode;
    }

    public String getBankCode() 
    {
        return bankCode;
    }
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankDescribe(String bankDescribe) 
    {
        this.bankDescribe = bankDescribe;
    }

    public String getBankDescribe() 
    {
        return bankDescribe;
    }
    public void setBankVersion(String bankVersion) 
    {
        this.bankVersion = bankVersion;
    }

    public String getBankVersion() 
    {
        return bankVersion;
    }
    public void setExamType(Long examType) 
    {
        this.examType = examType;
    }

    public Long getExamType() 
    {
        return examType;
    }
    public void setOnlineDate(Date onlineDate) 
    {
        this.onlineDate = onlineDate;
    }

    public Date getOnlineDate() 
    {
        return onlineDate;
    }
    public void setPictureUrl(String pictureUrl) 
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() 
    {
        return pictureUrl;
    }
    public void setRadioScore(Long radioScore) 
    {
        this.radioScore = radioScore;
    }

    public int getRadioNumber() {
		return radioNumber;
	}

	public void setRadioNumber(int radioNumber) {
		this.radioNumber = radioNumber;
	}

	public int getChoiceNumber() {
		return choiceNumber;
	}

	public void setChoiceNumber(int choiceNumber) {
		this.choiceNumber = choiceNumber;
	}

	public int getJudgeNumber() {
		return judgeNumber;
	}

	public void setJudgeNumber(int judgeNumber) {
		this.judgeNumber = judgeNumber;
	}

    public double getRadioScore() 
    {
        return radioScore;
    }
    public void setChoiceScore(Long choiceScore) 
    {
        this.choiceScore = choiceScore;
    }

    public double getChoiceScore() 
    {
        return choiceScore;
    }
    public void setJudgeScore(Long judgeScore) 
    {
        this.judgeScore = judgeScore;
    }

    public double getJudgeScore() 
    {
        return judgeScore;
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
            .append("bankId", getBankId())
            .append("bankCode", getBankCode())
            .append("bankName", getBankName())
            .append("bankDescribe", getBankDescribe())
            .append("bankVersion", getBankVersion())
            .append("examType", getExamType())
            .append("typeName", getTypeName())
            .append("onlineDate", getOnlineDate())
            .append("pictureUrl", getPictureUrl())
            .append("radioNumber", getRadioNumber())
            .append("choiceNumber", getChoiceNumber())
            .append("judgeNumber", getJudgeNumber())
            .append("radioScore", getRadioScore())
            .append("choiceScore", getChoiceScore())
            .append("judgeScore", getJudgeScore())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createDept", getCreateDept())
            .append("createTime", getCreateTime())
            .toString();
    }
}
