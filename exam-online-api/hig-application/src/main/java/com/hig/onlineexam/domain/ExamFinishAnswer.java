package com.hig.onlineexam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试成绩对象 exam_finish_answer
 * 
 * @author qnsdt
 * @date 2023-03-02
 */
public class ExamFinishAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试代码 */
    private String examCode;

    /** 考试用户 */
    private String userCode;

    /** 题目代码 */
    private String questionsCode;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String rightAnswer;

    /** 分数 */
    @Excel(name = "分数")
    private Long examScore;

    /** 题目回答 */
    @Excel(name = "题目回答")
    private String questionsAnswer;

    /** 题目得分 */
    @Excel(name = "题目得分")
    private Long questionsScore;

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
    public void setRightAnswer(String rightAnswer) 
    {
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswer() 
    {
        return rightAnswer;
    }
    public void setExamScore(Long examScore) 
    {
        this.examScore = examScore;
    }

    public Long getExamScore() 
    {
        return examScore;
    }
    public void setQuestionsAnswer(String questionsAnswer) 
    {
        this.questionsAnswer = questionsAnswer;
    }

    public String getQuestionsAnswer() 
    {
        return questionsAnswer;
    }
    public void setQuestionsScore(Long questionsScore) 
    {
        this.questionsScore = questionsScore;
    }

    public Long getQuestionsScore() 
    {
        return questionsScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examCode", getExamCode())
            .append("userCode", getUserCode())
            .append("questionsCode", getQuestionsCode())
            .append("rightAnswer", getRightAnswer())
            .append("examScore", getExamScore())
            .append("questionsAnswer", getQuestionsAnswer())
            .append("questionsScore", getQuestionsScore())
            .toString();
    }
}
