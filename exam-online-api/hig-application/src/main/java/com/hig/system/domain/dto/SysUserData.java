package com.hig.system.domain.dto;

import lombok.Data;

@Data
public class SysUserData {
	private int userId;
	private String userCode;
	private String userName;
	private String nickName;
	private int deptId;
	private String deptName;
	private String email;
	private String phonenumber;
	private String sex;
	private String avatar;
    private int status;
    private String remark;
    private String choiceCode;
    private int orderId;
    
    
}
