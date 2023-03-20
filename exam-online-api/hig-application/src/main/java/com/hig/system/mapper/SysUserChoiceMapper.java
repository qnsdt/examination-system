package com.hig.system.mapper;

import java.util.List;
import com.hig.system.domain.SysUserChoice;
import com.hig.system.domain.dto.SysUserCopy;
import com.hig.system.domain.dto.SysUserData;

/**
 * 选择用户Mapper接口
 * 
 * @author qnsdt
 * @date 2023-02-02
 */
public interface SysUserChoiceMapper 
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


    public int copySysUser(SysUserCopy sysUserCopy);
    
    public int copyTempUser(SysUserCopy sysUserCopy);
    /**
     * 新增选择用户
     * 
     * @param sysUserChoice 选择用户
     * @return 结果
     */
    public int insertSysUserChoice(SysUserChoice sysUserChoice);

    /**
     * 修改选择用户
     * 
     * @param sysUserChoice 选择用户
     * @return 结果
     */
    public int updateSysUserChoice(SysUserChoice sysUserChoice);

    /**
     * 删除选择用户
     * 
     * @param choiceCode 选择用户ID
     * @return 结果
     */
    public int deleteSysUserChoiceById(String choiceCode);

    /**
     * 批量删除选择用户
     * 
     * @param choiceCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserChoiceByIds(String[] choiceCodes);
}
