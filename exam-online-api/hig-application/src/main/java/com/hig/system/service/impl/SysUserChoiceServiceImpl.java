package com.hig.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.system.mapper.SysUserChoiceMapper;
import com.hig.system.domain.SysUserChoice;
import com.hig.system.domain.dto.SysUserCopy;
import com.hig.system.domain.dto.SysUserData;
import com.hig.system.service.ISysUserChoiceService;

/**
 * 选择用户Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-02-02
 */
@Service
public class SysUserChoiceServiceImpl implements ISysUserChoiceService 
{
    @Autowired
    private SysUserChoiceMapper sysUserChoiceMapper;

    /**
     * 查询选择用户
     * 
     * @param choiceCode 选择用户ID
     * @return 选择用户
     */
    @Override
    public SysUserChoice selectSysUserChoiceById(String choiceCode)
    {
        return sysUserChoiceMapper.selectSysUserChoiceById(choiceCode);
    }

    /**
     * 查询选择用户列表
     * 
     * @param sysUserChoice 选择用户
     * @return 选择用户
     */
    @Override
    public List<SysUserChoice> selectSysUserChoiceList(SysUserChoice sysUserChoice)
    {
        return sysUserChoiceMapper.selectSysUserChoiceList(sysUserChoice);
    }

    /**
     * 新增选择用户
     * 
     * @param sysUserChoice 选择用户
     * @return 结果
     */
    @Override
    public int insertSysUserChoice(SysUserChoice sysUserChoice)
    {
        return sysUserChoiceMapper.insertSysUserChoice(sysUserChoice);
    }

    /**
     * 修改选择用户
     * 
     * @param sysUserChoice 选择用户
     * @return 结果
     */
    @Override
    public int updateSysUserChoice(SysUserChoice sysUserChoice)
    {
        return sysUserChoiceMapper.updateSysUserChoice(sysUserChoice);
    }

    /**
     * 批量删除选择用户
     * 
     * @param choiceCodes 需要删除的选择用户ID
     * @return 结果
     */
    @Override
    public int deleteSysUserChoiceByIds(String[] choiceCodes)
    {
        return sysUserChoiceMapper.deleteSysUserChoiceByIds(choiceCodes);
    }

    /**
     * 删除选择用户信息
     * 
     * @param choiceCode 选择用户ID
     * @return 结果
     */
    @Override
    public int deleteSysUserChoiceById(String choiceCode)
    {
        return sysUserChoiceMapper.deleteSysUserChoiceById(choiceCode);
    }

	@Override
	public int copySysUser(SysUserCopy sysUserCopy) {
		// TODO Auto-generated method stub
		return sysUserChoiceMapper.copySysUser(sysUserCopy);
	}

	@Override
	public int copyTempUser(SysUserCopy sysUserCopy) {
		// TODO Auto-generated method stub
		return sysUserChoiceMapper.copyTempUser(sysUserCopy);
	}

	@Override
	public List<SysUserData> selectGroupUserList(SysUserData sysUser) {
		// TODO Auto-generated method stub
		return sysUserChoiceMapper.selectGroupUserList(sysUser);
	}
}
