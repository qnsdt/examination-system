package com.hig.questions.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题目属性对象 exam_questions_property
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public class ExamQuestionsProperty extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private int rownum;
    
    public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

    /** 题库代码 */
    @Excel(name = "题库代码")
    private String bankCode;

    /** 试题id */
    @Excel(name = "试题id")
    private Long questionsId;

    /** 试题代码 */
    private String questionsCode;

    /** 试题题目 */
    @Excel(name = "试题题目")
    private String questionsTitle;

    /** 试题类型 */
    @Excel(name = "试题类型")
    private Integer questionsType;

    /** 题目分数 */
    @Excel(name = "题目分数")
    private Integer questionsScore;

    /** 复杂度 */
    @Excel(name = "复杂度")
    private Integer rateNumber;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String rightAnswer;

    /** 答案分析 */
    @Excel(name = "答案分析")
    private String answerAnalyse;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    private String createBy;
    
    public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/** 创建部门 */
    private Long createDept;

    public void setBankCode(String bankCode) 
    {
        this.bankCode = bankCode;
    }

    public String getBankCode() 
    {
        return bankCode;
    }
    public void setQuestionsId(Long questionsId) 
    {
        this.questionsId = questionsId;
    }

    public Long getQuestionsId() 
    {
        return questionsId;
    }
    public void setQuestionsCode(String questionsCode) 
    {
        this.questionsCode = questionsCode;
    }

    public String getQuestionsCode() 
    {
        return questionsCode;
    }
    public void setQuestionsTitle(String questionsTitle) 
    {
        this.questionsTitle = questionsTitle;
    }

    public String getQuestionsTitle() 
    {
        return questionsTitle;
    }
    public void setQuestionsType(Integer questionsType) 
    {
        this.questionsType = questionsType;
    }

    public Integer getQuestionsType() 
    {
        return questionsType;
    }
    public void setQuestionsScore(Integer questionsScore) 
    {
        this.questionsScore = questionsScore;
    }

    public Integer getQuestionsScore() 
    {
        return questionsScore;
    }
    public void setRateNumber(Integer rateNumber) 
    {
        this.rateNumber = rateNumber;
    }

    public Integer getRateNumber() 
    {
        return rateNumber;
    }
    public void setRightAnswer(String rightAnswer) 
    {
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswer() 
    {
        return rightAnswer;
    }
    public void setAnswerAnalyse(String answerAnalyse) 
    {
        this.answerAnalyse = answerAnalyse;
    }

    public String getAnswerAnalyse() 
    {
        return answerAnalyse;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
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

    
    
	public ExamQuestionsProperty() {
		super();
	}

	public ExamQuestionsProperty(int rownum, String bankCode, Long questionsId, String questionsCode,
			String questionsTitle, Integer questionsType, Integer questionsScore, Integer rateNumber,
			String rightAnswer, String answerAnalyse, Integer status, String createBy, Long createDept) {
		super();
		this.rownum = rownum;
		this.bankCode = bankCode;
		this.questionsId = questionsId;
		this.questionsCode = questionsCode;
		this.questionsTitle = questionsTitle;
		this.questionsType = questionsType;
		this.questionsScore = questionsScore;
		this.rateNumber = rateNumber;
		this.rightAnswer = rightAnswer;
		this.answerAnalyse = answerAnalyse;
		this.status = status;
		this.createBy = createBy;
		this.createDept = createDept;
	}

	public ExamQuestionsProperty(String bankCode, Long questionsId, String questionsCode, String questionsTitle,
			Integer questionsType, Integer questionsScore, Integer rateNumber, String rightAnswer, String answerAnalyse,
			Integer status, String createBy, Long createDept) {
		super();
		this.bankCode = bankCode;
		this.questionsId = questionsId;
		this.questionsCode = questionsCode;
		this.questionsTitle = questionsTitle;
		this.questionsType = questionsType;
		this.questionsScore = questionsScore;
		this.rateNumber = rateNumber;
		this.rightAnswer = rightAnswer;
		this.answerAnalyse = answerAnalyse;
		this.status = status;
		this.createBy = createBy;
		this.createDept = createDept;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bankCode", getBankCode())
            .append("questionsId", getQuestionsId())
            .append("questionsCode", getQuestionsCode())
            .append("questionsTitle", getQuestionsTitle())
            .append("questionsType", getQuestionsType())
            .append("questionsScore", getQuestionsScore())
            .append("rateNumber", getRateNumber())
            .append("rightAnswer", getRightAnswer())
            .append("answerAnalyse", getAnswerAnalyse())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createDept", getCreateDept())
            .append("createTime", getCreateTime())
            .toString();
    }
}
