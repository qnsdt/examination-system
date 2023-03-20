package com.hig.questions.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 题目内容表对象 exam_questions_content
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public class ExamQuestionsContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 试题代码 */
    @Excel(name = "试题代码")
    private String questionsCode;

    /** 试题题目 */
    @Excel(name = "试题题目")
    private String questionsContent;

    public void setQuestionsCode(String questionsCode) 
    {
        this.questionsCode = questionsCode;
    }

    public String getQuestionsCode() 
    {
        return questionsCode;
    }
    public void setQuestionsContent(String questionsContent) 
    {
        this.questionsContent = questionsContent;
    }

    public String getQuestionsContent() 
    {
        return questionsContent;
    }

    
    public ExamQuestionsContent(String questionsCode, String questionsContent) {
		super();
		this.questionsCode = questionsCode;
		this.questionsContent = questionsContent;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionsCode", getQuestionsCode())
            .append("questionsContent", getQuestionsContent())
            .toString();
    }
}
