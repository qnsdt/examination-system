package com.hig.exam.mapper;

import java.util.List;
import com.hig.exam.domain.ExamTaskGroup;

/**
 * 任务分组Mapper接口
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
public interface ExamTaskGroupMapper 
{
    /**
     * 查询任务分组
     * 
     * @param examCode 任务分组ID
     * @return 任务分组
     */
    public ExamTaskGroup selectExamTaskGroupById(String examCode);

    /**
     * 查询任务分组列表
     * 
     * @param examTaskGroup 任务分组
     * @return 任务分组集合
     */
    public List<ExamTaskGroup> selectExamTaskGroupList(ExamTaskGroup examTaskGroup);

    /**
     * 新增任务分组
     * 
     * @param examTaskGroup 任务分组
     * @return 结果
     */
    public int insertExamTaskGroup(ExamTaskGroup examTaskGroup);

    /**
     * 修改任务分组
     * 
     * @param examTaskGroup 任务分组
     * @return 结果
     */
    public int updateExamTaskGroup(ExamTaskGroup examTaskGroup);

    /**
     * 删除任务分组
     * 
     * @param examCode 任务分组ID
     * @return 结果
     */
    public int deleteExamTaskGroupById(String examCode);

    /**
     * 批量删除任务分组
     * 
     * @param examCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamTaskGroupByIds(String[] examCodes);
}
