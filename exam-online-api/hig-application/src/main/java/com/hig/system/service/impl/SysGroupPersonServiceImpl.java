package com.hig.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.system.mapper.SysGroupPersonMapper;
import com.hig.system.domain.SysGroupPerson;
import com.hig.system.service.ISysGroupPersonService;

/**
 * 分组用户Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-02-06
 */
@Service
public class SysGroupPersonServiceImpl implements ISysGroupPersonService 
{
    @Autowired
    private SysGroupPersonMapper sysGroupPersonMapper;

    /**
     * 查询分组用户
     * 
     * @param groupCode 分组用户ID
     * @return 分组用户
     */
    @Override
    public SysGroupPerson selectSysGroupPersonById(String groupCode)
    {
        return sysGroupPersonMapper.selectSysGroupPersonById(groupCode);
    }

    /**
     * 查询分组用户列表
     * 
     * @param sysGroupPerson 分组用户
     * @return 分组用户
     */
    @Override
    public List<SysGroupPerson> selectSysGroupPersonList(SysGroupPerson sysGroupPerson)
    {
        return sysGroupPersonMapper.selectSysGroupPersonList(sysGroupPerson);
    }

    /**
     * 新增分组用户
     * 
     * @param sysGroupPerson 分组用户
     * @return 结果
     */
    @Override
    public int insertSysGroupPerson(List<SysGroupPerson> sysGroupPersonList)
    {    	
    	sysGroupPersonMapper.deleteSysGroupPersonById(sysGroupPersonList.get(0).getGroupCode());
    	int count = 0;
    	
    	for (SysGroupPerson sysGroupPerson: sysGroupPersonList) {
    		count += sysGroupPersonMapper.insertSysGroupPerson(sysGroupPerson);
    	}
    	
        return count;
    }

    /**
     * 修改分组用户
     * 
     * @param sysGroupPerson 分组用户
     * @return 结果
     */
    @Override
    public int updateSysGroupPerson(SysGroupPerson sysGroupPerson)
    {
        return sysGroupPersonMapper.updateSysGroupPerson(sysGroupPerson);
    }

    /**
     * 批量删除分组用户
     * 
     * @param groupCodes 需要删除的分组用户ID
     * @return 结果
     */
    @Override
    public int deleteSysGroupPersonByIds(String[] groupCodes)
    {
        return sysGroupPersonMapper.deleteSysGroupPersonByIds(groupCodes);
    }

    /**
     * 删除分组用户信息
     * 
     * @param groupCode 分组用户ID
     * @return 结果
     */
    @Override
    public int deleteSysGroupPersonById(String groupCode)
    {
        return sysGroupPersonMapper.deleteSysGroupPersonById(groupCode);
    }
}
