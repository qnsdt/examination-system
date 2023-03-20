package com.hig.questions.service;

import java.util.List;
import com.hig.questions.domain.ExamQuestionsProperty;

/**
 * 题目属性Service接口
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public interface IExamQuestionsPropertyService 
{
    /**
     * 查询题目属性
     * 
     * @param questionsCode 题目属性ID
     * @return 题目属性
     */
    public ExamQuestionsProperty selectExamQuestionsPropertyById(String questionsCode);

    /**
     * 查询题目属性列表
     * 
     * @param examQuestionsProperty 题目属性
     * @return 题目属性集合
     */
    public List<ExamQuestionsProperty> selectExamQuestionsPropertyList(ExamQuestionsProperty examQuestionsProperty);

    /**
     * 新增题目属性
     * 
     * @param examQuestionsProperty 题目属性
     * @return 结果
     */
    public int insertExamQuestionsProperty(ExamQuestionsProperty examQuestionsProperty);

    /**
     * 修改题目属性
     * 
     * @param examQuestionsProperty 题目属性
     * @return 结果
     */
    public int updateExamQuestionsProperty(ExamQuestionsProperty examQuestionsProperty);

    /**
     * 批量删除题目属性
     * 
     * @param questionsCodes 需要删除的题目属性ID
     * @return 结果
     */
    public int deleteExamQuestionsPropertyByIds(String[] questionsCodes);

    /**
     * 删除题目属性信息
     * 
     * @param questionsCode 题目属性ID
     * @return 结果
     */
    public int deleteExamQuestionsPropertyById(String questionsCode);
}
