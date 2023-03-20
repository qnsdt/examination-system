package com.hig.onlineexam.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试成绩对象 exam_user_score
 * 
 * @author qnsdt
 * @date 2023-03-03
 */
public class ExamUserScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试代码 */
    private String examCode;

    /** 考试人员 */
    private String userCode;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 分数 */
    @Excel(name = "分数")
    private Integer examScore;

    /** 题目分数 */
    @Excel(name = "题目分数")
    private Integer questionsScore;

    /** 题目数 */
    @Excel(name = "题目数")
    private Integer examNumber;

    /** 答题数 */
    @Excel(name = "答题数")
    private Integer answeredNumber;

    /** 未答题数 */
    @Excel(name = "未答题数")
    private Integer notAnswered;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

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
    public void setExamScore(Integer examScore) 
    {
        this.examScore = examScore;
    }

    public Integer getExamScore() 
    {
        return examScore;
    }
    public void setQuestionsScore(Integer questionsScore) 
    {
        this.questionsScore = questionsScore;
    }

    public Integer getQuestionsScore() 
    {
        return questionsScore;
    }
    public void setExamNumber(Integer examNumber) 
    {
        this.examNumber = examNumber;
    }

    public Integer getExamNumber() 
    {
        return examNumber;
    }
    public void setAnsweredNumber(Integer answeredNumber) 
    {
        this.answeredNumber = answeredNumber;
    }

    public Integer getAnsweredNumber() 
    {
        return answeredNumber;
    }
    public void setNotAnswered(Integer notAnswered) 
    {
        this.notAnswered = notAnswered;
    }

    public Integer getNotAnswered() 
    {
        return notAnswered;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("examCode", getExamCode())
            .append("userCode", getUserCode())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("examScore", getExamScore())
            .append("questionsScore", getQuestionsScore())
            .append("examNumber", getExamNumber())
            .append("answeredNumber", getAnsweredNumber())
            .append("notAnswered", getNotAnswered())
            .append("status", getStatus())
            .toString();
    }
}
