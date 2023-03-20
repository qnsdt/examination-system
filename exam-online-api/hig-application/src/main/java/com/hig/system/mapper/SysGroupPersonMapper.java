package com.hig.system.mapper;

import java.util.List;
import com.hig.system.domain.SysGroupPerson;

/**
 * 分组用户Mapper接口
 * 
 * @author qnsdt
 * @date 2023-02-06
 */
public interface SysGroupPersonMapper 
{
    /**
     * 查询分组用户
     * 
     * @param groupCode 分组用户ID
     * @return 分组用户
     */
    public SysGroupPerson selectSysGroupPersonById(String groupCode);

    /**
     * 查询分组用户列表
     * 
     * @param sysGroupPerson 分组用户
     * @return 分组用户集合
     */
    public List<SysGroupPerson> selectSysGroupPersonList(SysGroupPerson sysGroupPerson);

    /**
     * 新增分组用户
     * 
     * @param sysGroupPerson 分组用户
     * @return 结果
     */
    public int insertSysGroupPerson(SysGroupPerson sysGroupPerson);

    /**
     * 修改分组用户
     * 
     * @param sysGroupPerson 分组用户
     * @return 结果
     */
    public int updateSysGroupPerson(SysGroupPerson sysGroupPerson);

    /**
     * 删除分组用户
     * 
     * @param groupCode 分组用户ID
     * @return 结果
     */
    public int deleteSysGroupPersonById(String groupCode);

    /**
     * 批量删除分组用户
     * 
     * @param groupCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysGroupPersonByIds(String[] groupCodes);
}
