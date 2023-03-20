package com.hig.questions.mapper;

import java.util.List;
import com.hig.questions.domain.ExamQuestionsContent;

/**
 * 题目内容表Mapper接口
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public interface ExamQuestionsContentMapper 
{
    /**
     * 查询题目内容表
     * 
     * @param questionsCode 题目内容表ID
     * @return 题目内容表
     */
    public ExamQuestionsContent selectExamQuestionsContentById(String questionsCode);

    /**
     * 查询题目内容表列表
     * 
     * @param examQuestionsContent 题目内容表
     * @return 题目内容表集合
     */
    public List<ExamQuestionsContent> selectExamQuestionsContentList(ExamQuestionsContent examQuestionsContent);

    /**
     * 新增题目内容表
     * 
     * @param examQuestionsContent 题目内容表
     * @return 结果
     */
    public int insertExamQuestionsContent(ExamQuestionsContent examQuestionsContent);

    /**
     * 修改题目内容表
     * 
     * @param examQuestionsContent 题目内容表
     * @return 结果
     */
    public int updateExamQuestionsContent(ExamQuestionsContent examQuestionsContent);

    /**
     * 删除题目内容表
     * 
     * @param questionsCode 题目内容表ID
     * @return 结果
     */
    public int deleteExamQuestionsContentById(String questionsCode);

    /**
     * 批量删除题目内容表
     * 
     * @param questionsCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamQuestionsContentByIds(String[] questionsCodes);
}
