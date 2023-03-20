package com.hig.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.system.mapper.SysGroupMapper;
import com.hig.system.domain.SysGroup;
import com.hig.system.service.ISysGroupService;

/**
 * 人员分组Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-01-28
 */
@Service
public class SysGroupServiceImpl implements ISysGroupService 
{
    @Autowired
    private SysGroupMapper sysGroupMapper;

    /**
     * 查询人员分组
     * 
     * @param groupId 人员分组ID
     * @return 人员分组
     */
    @Override
    public SysGroup selectSysGroupById(Long groupId)
    {
        return sysGroupMapper.selectSysGroupById(groupId);
    }

    /**
     * 查询人员分组列表
     * 
     * @param sysGroup 人员分组
     * @return 人员分组
     */
    @Override
    public List<SysGroup> selectSysGroupList(SysGroup sysGroup)
    {
        return sysGroupMapper.selectSysGroupList(sysGroup);
    }

    /**
     * 新增人员分组
     * 
     * @param sysGroup 人员分组
     * @return 结果
     */
    @Override
    public int insertSysGroup(SysGroup sysGroup)
    {
        sysGroup.setCreateTime(DateUtils.getNowDate());
        return sysGroupMapper.insertSysGroup(sysGroup);
    }

    /**
     * 修改人员分组
     * 
     * @param sysGroup 人员分组
     * @return 结果
     */
    @Override
    public int updateSysGroup(SysGroup sysGroup)
    {
        return sysGroupMapper.updateSysGroup(sysGroup);
    }

    /**
     * 批量删除人员分组
     * 
     * @param groupIds 需要删除的人员分组ID
     * @return 结果
     */
    @Override
    public int deleteSysGroupByIds(Long[] groupIds)
    {
        return sysGroupMapper.deleteSysGroupByIds(groupIds);
    }

    /**
     * 删除人员分组信息
     * 
     * @param groupId 人员分组ID
     * @return 结果
     */
    @Override
    public int deleteSysGroupById(Long groupId)
    {
        return sysGroupMapper.deleteSysGroupById(groupId);
    }

	@Override
	public List<SysGroup> selectChoiceSysGroupList(String examCode) {
		// TODO Auto-generated method stub
		return sysGroupMapper.selectChoiceSysGroupList(examCode);
	}
}
