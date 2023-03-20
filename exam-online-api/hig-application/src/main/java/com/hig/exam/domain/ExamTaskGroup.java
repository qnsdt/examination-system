package com.hig.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务分组对象 exam_task_group
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
public class ExamTaskGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试代码 */
    @Excel(name = "考试代码")
    private String examCode;

    /** 分组代码 */
    @Excel(name = "分组代码")
    private String groupCode;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long orderId;

    public void setExamCode(String examCode) 
    {
        this.examCode = examCode;
    }

    public String getExamCode() 
    {
        return examCode;
    }
    public void setGroupCode(String groupCode) 
    {
        this.groupCode = groupCode;
    }

    public String getGroupCode() 
    {
        return groupCode;
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
            .append("examCode", getExamCode())
            .append("groupCode", getGroupCode())
            .append("orderId", getOrderId())
            .toString();
    }
}
