package com.hig.questions.service;

import java.util.List;
import com.hig.questions.domain.ExamQuestions;

/**
 * 考试题目Service接口
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public interface IExamQuestionsService 
{
    /**
     * 查询考试题目
     * 
     * @param questionsCode 考试题目ID
     * @return 考试题目
     */
    public ExamQuestions selectExamQuestionsById(String questionsCode);

    /**
     * 查询考试题目列表
     * 
     * @param examQuestions 考试题目
     * @return 考试题目集合
     */
    public List<ExamQuestions> selectExamQuestionsList(ExamQuestions examQuestions);

    /**
     * 新增考试题目
     * 
     * @param examQuestions 考试题目
     * @return 结果
     */
    public int insertExamQuestions(ExamQuestions examQuestions);

    /**
     * 修改考试题目
     * 
     * @param examQuestions 考试题目
     * @return 结果
     */
    public int updateExamQuestions(ExamQuestions examQuestions);

    /**
     * 批量删除考试题目
     * 
     * @param questionsCodes 需要删除的考试题目ID
     * @return 结果
     */
    public int deleteExamQuestionsByIds(String[] questionsCodes);

    /**
     * 删除考试题目信息
     * 
     * @param questionsCode 考试题目ID
     * @return 结果
     */
    public int deleteExamQuestionsById(String questionsCode);
}
