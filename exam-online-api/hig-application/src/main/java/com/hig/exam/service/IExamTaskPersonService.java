package com.hig.exam.service;

import java.util.List;

import com.hig.exam.domain.ExamTaskManager;
import com.hig.exam.domain.ExamTaskPerson;

/**
 * 考试人员Service接口
 * 
 * @author qnsdt
 * @date 2023-01-06
 */
public interface IExamTaskPersonService 
{
    /**
     * 查询考试人员
     * 
     * @param examCode 考试人员ID
     * @return 考试人员
     */
    public ExamTaskPerson selectExamTaskPersonById(String examCode);

    /**
     * 查询考试人员
     * 
     * @param examTaskPerson 考试人员
     * @return 考试人员
     */
    public ExamTaskPerson selectExamTaskPerson(ExamTaskPerson examTaskPerson);

    /**
     * 查询考试人员列表
     * 
     * @param examTaskPerson 考试人员
     * @return 考试人员集合
     */
    public List<ExamTaskPerson> selectExamTaskPersonList(ExamTaskPerson examTaskPerson);

    /**
     * 新增考试人员
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    public int insertExamTaskPerson(ExamTaskPerson examTaskPerson);

    /**
     * 批量拷贝考试人员
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    public int batchInsertPerson(String examCode);

    /**
     * 修改考试人员
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    public int updateExamTaskPerson(ExamTaskPerson examTaskPerson);

    
    /**
     * 更新考试开始时间
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    public int updateStartTime(ExamTaskPerson examTaskPerson);
    
    /**
     * 更新完成状态
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    public int updateDoneStatus(ExamTaskPerson examTaskPerson);


    /**
     * 批量删除考试人员
     * 
     * @param examCodes 需要删除的考试人员ID
     * @return 结果
     */
    public int deleteExamTaskPersonByIds(String[] examCodes);

    /**
     * 删除考试人员信息
     * 
     * @param examCode 考试人员ID
     * @return 结果
     */
    public int deleteExamTaskPersonById(String examCode);
}
