package com.hig.questions.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.questions.mapper.ExamQuestionsContentMapper;
import com.hig.questions.domain.ExamQuestionsContent;
import com.hig.questions.service.IExamQuestionsContentService;

/**
 * 题目内容表Service业务层处理
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
@Service
public class ExamQuestionsContentServiceImpl implements IExamQuestionsContentService 
{
    @Autowired
    private ExamQuestionsContentMapper examQuestionsContentMapper;

    /**
     * 查询题目内容表
     * 
     * @param questionsCode 题目内容表ID
     * @return 题目内容表
     */
    @Override
    public ExamQuestionsContent selectExamQuestionsContentById(String questionsCode)
    {
        return examQuestionsContentMapper.selectExamQuestionsContentById(questionsCode);
    }

    /**
     * 查询题目内容表列表
     * 
     * @param examQuestionsContent 题目内容表
     * @return 题目内容表
     */
    @Override
    public List<ExamQuestionsContent> selectExamQuestionsContentList(ExamQuestionsContent examQuestionsContent)
    {
        return examQuestionsContentMapper.selectExamQuestionsContentList(examQuestionsContent);
    }

    /**
     * 新增题目内容表
     * 
     * @param examQuestionsContent 题目内容表
     * @return 结果
     */
    @Override
    public int insertExamQuestionsContent(ExamQuestionsContent examQuestionsContent)
    {
        return examQuestionsContentMapper.insertExamQuestionsContent(examQuestionsContent);
    }

    /**
     * 修改题目内容表
     * 
     * @param examQuestionsContent 题目内容表
     * @return 结果
     */
    @Override
    public int updateExamQuestionsContent(ExamQuestionsContent examQuestionsContent)
    {
        return examQuestionsContentMapper.updateExamQuestionsContent(examQuestionsContent);
    }

    /**
     * 批量删除题目内容表
     * 
     * @param questionsCodes 需要删除的题目内容表ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsContentByIds(String[] questionsCodes)
    {
        return examQuestionsContentMapper.deleteExamQuestionsContentByIds(questionsCodes);
    }

    /**
     * 删除题目内容表信息
     * 
     * @param questionsCode 题目内容表ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsContentById(String questionsCode)
    {
        return examQuestionsContentMapper.deleteExamQuestionsContentById(questionsCode);
    }
}
