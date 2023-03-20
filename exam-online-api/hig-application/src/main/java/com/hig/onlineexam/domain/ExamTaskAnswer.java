package com.hig.onlineexam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试实时数据对象 exam_task_answer
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
public class ExamTaskAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试代码 */
    @Excel(name = "考试代码")
    private String examCode;

    /** 考试用户 */
    @Excel(name = "考试用户")
    private String userCode;

    /** 题目代码 */
    @Excel(name = "题目代码")
    private String questionsCode;

    /** 题号 */
    @Excel(name = "题号")
    private Long questionsNumber;

    /** 试题类型 */
    @Excel(name = "试题类型")
    private Long questionsType;

    /** 题目回答 */
    @Excel(name = "题目回答")
    private String questionsAnswer;

    /** 是否标记 */
    @Excel(name = "是否标记")
    private String isMark;

    /** 是否当前 */
    @Excel(name = "是否当前")
    private String isCurrent;

    public void setExamCode(String examCode) 
    {
        this.examCode = examCode;
    }

    public String getExamCode() 
    {
        return examCode;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
    }
    public void setQuestionsCode(String questionsCode) 
    {
        this.questionsCode = questionsCode;
    }

    public String getQuestionsCode() 
    {
        return questionsCode;
    }
    public void setQuestionsNumber(Long questionsNumber) 
    {
        this.questionsNumber = questionsNumber;
    }

    public Long getQuestionsNumber() 
    {
        return questionsNumber;
    }
    public void setQuestionsType(Long questionsType) 
    {
        this.questionsType = questionsType;
    }

    public Long getQuestionsType() 
    {
        return questionsType;
    }
    public void setQuestionsAnswer(String questionsAnswer) 
    {
        this.questionsAnswer = questionsAnswer;
    }

    public String getQuestionsAnswer() 
    {
        return questionsAnswer;
    }
    public void setIsMark(String isMark) 
    {
        this.isMark = isMark;
    }

    public String getIsMark() 
    {
        return isMark;
    }
    public void setIsCurrent(String isCurrent) 
    {
        this.isCurrent = isCurrent;
    }

    public String getIsCurrent() 
    {
        return isCurrent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examCode", getExamCode())
            .append("userCode", getUserCode())
            .append("questionsCode", getQuestionsCode())
            .append("questionsNumber", getQuestionsNumber())
            .append("questionsType", getQuestionsType())
            .append("questionsAnswer", getQuestionsAnswer())
            .append("isMark", getIsMark())
            .append("isCurrent", getIsCurrent())
            .toString();
    }
}
