package com.hig.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hig.system.domain.dto.SysUserData;
import com.hig.system.mapper.SysUserDataMapper;
import com.hig.system.service.ISysUserDataService;

@Service
public class SysUserDataServiceImpl implements ISysUserDataService {
	@Autowired
	SysUserDataMapper sysUserDataMapper;

	@Override
	public List<SysUserData> selectUserList(SysUserData sysUser) {
		// TODO Auto-generated method stub
		return sysUserDataMapper.selectUserList(sysUser);
	}

	@Override
	public List<SysUserData> selectChoiceUserList(SysUserData sysUser) {
		// TODO Auto-generated method stub
		return sysUserDataMapper.selectChoiceUserList(sysUser);
	}

}
