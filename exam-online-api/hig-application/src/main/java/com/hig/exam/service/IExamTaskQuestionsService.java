package com.hig.exam.service;

import java.util.List;
import com.hig.exam.domain.ExamTaskQuestions;

/**
 * 任务题目Service接口
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
public interface IExamTaskQuestionsService 
{
    /**
     * 查询任务题目
     * 
     * @param examCode 任务题目ID
     * @return 任务题目
     */
    public ExamTaskQuestions selectExamTaskQuestionsById(String examCode);

    /**
     * 查询任务题目列表
     * 
     * @param examTaskQuestions 任务题目
     * @return 任务题目集合
     */
    public List<ExamTaskQuestions> selectExamTaskQuestionsList(ExamTaskQuestions examTaskQuestions);

    /**
     * 新增任务题目
     * 
     * @param examTaskQuestions 任务题目
     * @return 结果
     */
    public int insertExamTaskQuestions(ExamTaskQuestions examTaskQuestions);

    public int batchUpdateExamTaskQuestions(List<ExamTaskQuestions> examTaskQuestionsList) throws Exception;

    /**
     * 修改任务题目
     * 
     * @param examTaskQuestions 任务题目
     * @return 结果
     */
    public int updateExamTaskQuestions(ExamTaskQuestions examTaskQuestions);

    /**
     * 批量删除任务题目
     * 
     * @param examCodes 需要删除的任务题目ID
     * @return 结果
     */
    public int deleteExamTaskQuestionsByIds(String[] examCodes);

    /**
     * 删除任务题目信息
     * 
     * @param examCode 任务题目ID
     * @return 结果
     */
    public int deleteExamTaskQuestionsById(String examCode);
}
