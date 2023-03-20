package com.hig.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分组用户对象 sys_group_person
 * 
 * @author qnsdt
 * @date 2023-02-06
 */
public class SysGroupPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分组代码 */
    @Excel(name = "分组代码")
    private String groupCode;

    /** 人员代码 */
    @Excel(name = "人员代码")
    private String personCode;

    /** 组内排序 */
    @Excel(name = "组内排序")
    private Long orderId;

    public void setGroupCode(String groupCode) 
    {
        this.groupCode = groupCode;
    }

    public String getGroupCode() 
    {
        return groupCode;
    }
    public void setPersonCode(String personCode) 
    {
        this.personCode = personCode;
    }

    public String getPersonCode() 
    {
        return personCode;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    
    public SysGroupPerson() {
		super();
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupCode", getGroupCode())
            .append("personCode", getPersonCode())
            .append("orderId", getOrderId())
            .toString();
    }
}
