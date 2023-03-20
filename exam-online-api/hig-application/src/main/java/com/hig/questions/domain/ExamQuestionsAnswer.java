package com.hig.questions.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试题目答案对象 exam_questions_answer
 * 
 * @author qnsdt
 * @date 2022-12-29
 */
public class ExamQuestionsAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 试题代码 */
    @Excel(name = "试题代码")
    private String questionsCode;

    /** 试题选项 */
    @Excel(name = "试题选项")
    private String optionCode;

    /** 选项描述 */
    @Excel(name = "选项描述")
    private String optionDescribe;

    /** 是否正确 */
    @Excel(name = "是否正确")
    private Integer isRight;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long orderId;

    public void setQuestionsCode(String questionsCode) 
    {
        this.questionsCode = questionsCode;
    }

    public String getQuestionsCode() 
    {
        return questionsCode;
    }
    public void setOptionCode(String optionCode) 
    {
        this.optionCode = optionCode;
    }

    public String getOptionCode() 
    {
        return optionCode;
    }
    public void setOptionDescribe(String optionDescribe) 
    {
        this.optionDescribe = optionDescribe;
    }

    public String getOptionDescribe() 
    {
        return optionDescribe;
    }
    public void setIsRight(Integer isRight) 
    {
        this.isRight = isRight;
    }

    public Integer getIsRight() 
    {
        return isRight;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionsCode", getQuestionsCode())
            .append("optionCode", getOptionCode())
            .append("optionDescribe", getOptionDescribe())
            .append("isRight", getIsRight())
            .append("orderId", getOrderId())
            .toString();
    }
}
