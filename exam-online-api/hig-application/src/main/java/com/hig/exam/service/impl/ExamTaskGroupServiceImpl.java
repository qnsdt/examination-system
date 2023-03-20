package com.hig.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.exam.mapper.ExamTaskGroupMapper;
import com.hig.exam.domain.ExamTaskGroup;
import com.hig.exam.service.IExamTaskGroupService;
import com.hig.exam.service.IExamTaskPersonService;

/**
 * 任务分组Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
@Service
public class ExamTaskGroupServiceImpl implements IExamTaskGroupService 
{
    @Autowired
    private ExamTaskGroupMapper examTaskGroupMapper;

    @Autowired
    private IExamTaskPersonService examTaskPersonService;
    /**
     * 查询任务分组
     * 
     * @param examCode 任务分组ID
     * @return 任务分组
     */
    @Override
    public ExamTaskGroup selectExamTaskGroupById(String examCode)
    {
        return examTaskGroupMapper.selectExamTaskGroupById(examCode);
    }

    /**
     * 查询任务分组列表
     * 
     * @param examTaskGroup 任务分组
     * @return 任务分组
     */
    @Override
    public List<ExamTaskGroup> selectExamTaskGroupList(ExamTaskGroup examTaskGroup)
    {
        return examTaskGroupMapper.selectExamTaskGroupList(examTaskGroup);
    }

    /**
     * 新增任务分组
     * 
     * @param examTaskGroup 任务分组
     * @return 结果
     */
    @Override
    public int insertExamTaskGroup(ExamTaskGroup examTaskGroup)
    {
        return examTaskGroupMapper.insertExamTaskGroup(examTaskGroup);
    }

    /**
     * 修改任务分组
     * 
     * @param examTaskGroup 任务分组
     * @return 结果
     */
    @Override
    public int updateExamTaskGroup(ExamTaskGroup examTaskGroup)
    {
        return examTaskGroupMapper.updateExamTaskGroup(examTaskGroup);
    }

    /**
     * 批量删除任务分组
     * 
     * @param examCodes 需要删除的任务分组ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskGroupByIds(String[] examCodes)
    {
        return examTaskGroupMapper.deleteExamTaskGroupByIds(examCodes);
    }

    /**
     * 删除任务分组信息
     * 
     * @param examCode 任务分组ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskGroupById(String examCode)
    {
        return examTaskGroupMapper.deleteExamTaskGroupById(examCode);
    }

	@Override
	public int batchUpdateExamTaskGroup(List<ExamTaskGroup> examTaskGroupList) throws Exception {
		// TODO Auto-generated method stub
		// 安全删除
		int count = 0;
		String examCode = examTaskGroupList.get(0).getExamCode();
		examTaskGroupMapper.deleteExamTaskGroupById(examCode);
		
		for (ExamTaskGroup examTaskGroup: examTaskGroupList) {
			try
			{
				count = examTaskGroupMapper.insertExamTaskGroup(examTaskGroup);
			}
			catch(Exception e) {
				throw new Exception("保存分组信息出错,信息:" + e.getMessage());								
			}
		}
		
		// 安全删除
		examTaskPersonService.deleteExamTaskPersonById(examCode);
		
		try
		{
			count = examTaskPersonService.batchInsertPerson(examCode);
		}
		catch(Exception e) {
			throw new Exception("保存考试人员信息出错,信息:" + e.getMessage());											
		}
		return 1;
	}
}
