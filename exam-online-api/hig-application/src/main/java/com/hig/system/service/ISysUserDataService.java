package com.hig.system.service;

import java.util.List;

import com.hig.system.domain.dto.SysUserData;

public interface ISysUserDataService {
	public List<SysUserData> selectUserList(SysUserData sysUser);
	
	public List<SysUserData> selectChoiceUserList(SysUserData sysUser);


}
