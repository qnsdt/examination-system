package com.hig.system.mapper;

import java.util.List;

import com.hig.system.domain.dto.SysUserData;

public interface SysUserDataMapper {
	public List<SysUserData> selectUserList(SysUserData sysUser);
	
	public List<SysUserData> selectChoiceUserList(SysUserData sysUser);

}
