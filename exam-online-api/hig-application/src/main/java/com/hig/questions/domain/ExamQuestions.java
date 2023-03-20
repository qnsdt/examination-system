package com.hig.questions.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试题目对象 exam_questions
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public class ExamQuestions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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

    /** 试题内容 */
    @Excel(name = "试题内容")
    private String questionsContent;

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

    /** 创建部门 */
    @Excel(name = "创建部门")
    private Long createDept;

    /** 题目选项 */
    private List<ExamQuestionsAnswer> answerList ;
    
    
    public List<ExamQuestionsAnswer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<ExamQuestionsAnswer> answerList) {
		this.answerList = answerList;
	}

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
    public void setQuestionsContent(String questionsContent) 
    {
        this.questionsContent = questionsContent;
    }

    public String getQuestionsContent() 
    {
        return questionsContent;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bankCode", getBankCode())
            .append("questionsId", getQuestionsId())
            .append("questionsCode", getQuestionsCode())
            .append("questionsTitle", getQuestionsTitle())
            .append("questionsContent", getQuestionsContent())
            .append("questionsType", getQuestionsType())
            .append("questionsScore", getQuestionsScore())
            .append("rateNumber", getRateNumber())
            .append("rightAnswer", getRightAnswer())
            .append("answerAnalyse", getAnswerAnalyse())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createDept", getCreateDept())
            .append("createTime", getCreateTime())
            .append("answerList", getAnswerList())
            .toString();
    }
}
