package com.hig.onlineexam.mapper;

import java.util.List;
import com.hig.onlineexam.domain.ExamTaskAnswer;

/**
 * 考试实时数据Mapper接口
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
public interface ExamTaskAnswerMapper 
{
    /**
     * 查询考试实时数据
     * 
     * @param examCode 考试实时数据ID
     * @return 考试实时数据
     */
    public ExamTaskAnswer selectExamTaskAnswerById(String examCode);

    /**
     * 查询考试实时数据列表
     * 
     * @param examTaskAnswer 考试实时数据
     * @return 考试实时数据集合
     */
    public List<ExamTaskAnswer> selectExamTaskAnswerList(ExamTaskAnswer examTaskAnswer);

    /**
     * 新增考试实时数据
     * 
     * @param examTaskAnswer 考试实时数据
     * @return 结果
     */
    public int insertExamTaskAnswer(ExamTaskAnswer examTaskAnswer);

    /**
     * 修改考试实时数据
     * 
     * @param examTaskAnswer 考试实时数据
     * @return 结果
     */
    public int updateExamTaskAnswer(ExamTaskAnswer examTaskAnswer);

    /**
     * 删除个人单条考试实时数据
     * 
     * @param examCode 考试实时数据ID
     * @return 结果
     */
    public int deleteExamTaskAnswer(ExamTaskAnswer examTaskAnswer);
    /**
     * 删除考试实时数据
     * 
     * @param examCode 考试实时数据ID
     * @return 结果
     */
    public int deleteExamTaskAnswerById(String examCode);

    /**
     * 批量删除考试实时数据
     * 
     * @param examCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamTaskAnswerByIds(String[] examCodes);
}
