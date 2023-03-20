package com.hig.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务题目对象 exam_task_questions
 * 
 * @author qnsdt
 * @date 2023-02-15
 */
public class ExamTaskQuestions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试代码 */
    @Excel(name = "考试代码")
    private String examCode;

    /** 题目代码 */
    @Excel(name = "题目代码")
    private String questionsCode;

    /** 题号 */
    @Excel(name = "题号")
    private int questionsNumber;

    public void setExamCode(String examCode) 
    {
        this.examCode = examCode;
    }

    public String getExamCode() 
    {
        return examCode;
    }
    public void setQuestionsCode(String questionsCode) 
    {
        this.questionsCode = questionsCode;
    }

    public String getQuestionsCode() 
    {
        return questionsCode;
    }
    public void setQuestionsNumber(int questionsNumber) 
    {
        this.questionsNumber = questionsNumber;
    }

    public int getQuestionsNumber() 
    {
        return questionsNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examCode", getExamCode())
            .append("questionsCode", getQuestionsCode())
            .append("questionsNumber", getQuestionsNumber())
            .toString();
    }
}
