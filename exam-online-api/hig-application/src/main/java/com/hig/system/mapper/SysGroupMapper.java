package com.hig.system.mapper;

import java.util.List;
import com.hig.system.domain.SysGroup;

/**
 * 人员分组Mapper接口
 * 
 * @author qnsdt
 * @date 2023-01-28
 */
public interface SysGroupMapper 
{
    /**
     * 查询人员分组
     * 
     * @param groupId 人员分组ID
     * @return 人员分组
     */
    public SysGroup selectSysGroupById(Long groupId);

    /**
     * 查询人员分组列表
     * 
     * @param sysGroup 人员分组
     * @return 人员分组集合
     */
    public List<SysGroup> selectSysGroupList(SysGroup sysGroup);

    /**
     * 查询人员分组列表
     * 
     * @param sysGroup 人员分组
     * @return 人员分组集合
     */
    public List<SysGroup> selectChoiceSysGroupList(String examCode);

    /**
     * 新增人员分组
     * 
     * @param sysGroup 人员分组
     * @return 结果
     */
    public int insertSysGroup(SysGroup sysGroup);

    /**
     * 修改人员分组
     * 
     * @param sysGroup 人员分组
     * @return 结果
     */
    public int updateSysGroup(SysGroup sysGroup);

    /**
     * 删除人员分组
     * 
     * @param groupId 人员分组ID
     * @return 结果
     */
    public int deleteSysGroupById(Long groupId);

    /**
     * 批量删除人员分组
     * 
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysGroupByIds(Long[] groupIds);
}
