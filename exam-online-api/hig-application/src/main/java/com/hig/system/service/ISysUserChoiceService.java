package com.hig.system.service;

import java.util.List;
import com.hig.system.domain.SysUserChoice;
import com.hig.system.domain.dto.SysUserCopy;
import com.hig.system.domain.dto.SysUserData;

/**
 * 选择用户Service接口
 * 
 * @author qnsdt
 * @date 2023-02-02
 */
public interface ISysUserChoiceService 
{
    /**
     * 查询选择用户
     * 
     * @param choiceCode 选择用户ID
     * @return 选择用户
     */
    public SysUserChoice selectSysUserChoiceById(String choiceCode);

    /**
     * 查询选择用户列表
     * 
     * @param sysUserChoice 选择用户
     * @return 选择用户集合
     */
    public List<SysUserChoice> selectSysUserChoiceList(SysUserChoice sysUserChoice);

    /**
     * 查询选择用户列表
     * 
     * @param sysUserChoice 选择用户
     * @return 选择用户集合
     */    
	public List<SysUserData> selectGroupUserList(SysUserData sysUser);

    /**
     * 新增选择用户
     * 
     * @param sysUserChoice 选择用户
     * @return 结果
     */
    public int insertSysUserChoice(SysUserChoice sysUserChoice);

    public int copySysUser(SysUserCopy sysUserCopy);
    
    public int copyTempUser(SysUserCopy sysUserCopy);

    /**
     * 修改选择用户
     * 
     * @param sysUserChoice 选择用户
     * @return 结果
     */
    public int updateSysUserChoice(SysUserChoice sysUserChoice);

    /**
     * 批量删除选择用户
     * 
     * @param choiceCodes 需要删除的选择用户ID
     * @return 结果
     */
    public int deleteSysUserChoiceByIds(String[] choiceCodes);

    /**
     * 删除选择用户信息
     * 
     * @param choiceCode 选择用户ID
     * @return 结果
     */
    public int deleteSysUserChoiceById(String choiceCode);
}
