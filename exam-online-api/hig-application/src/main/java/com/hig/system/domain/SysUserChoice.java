package com.hig.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 选择用户对象 sys_user_choice
 * 
 * @author qnsdt
 * @date 2023-02-02
 */
public class SysUserChoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 选择代码 */
    @Excel(name = "选择代码")
    private String choiceCode;

    /** 用户代码 */
    @Excel(name = "用户代码")
    private String userCode;

    /** 内部排序 */
    @Excel(name = "内部排序")
    private Integer orderId;

    public void setChoiceCode(String choiceCode) 
    {
        this.choiceCode = choiceCode;
    }

    public String getChoiceCode() 
    {
        return choiceCode;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
    }
    public void setOrderId(Integer orderId) 
    {
        this.orderId = orderId;
    }

    public Integer getOrderId() 
    {
        return orderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("choiceCode", getChoiceCode())
            .append("userCode", getUserCode())
            .append("orderId", getOrderId())
            .toString();
    }
}
