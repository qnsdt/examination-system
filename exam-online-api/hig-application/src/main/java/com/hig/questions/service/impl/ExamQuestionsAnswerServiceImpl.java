package com.hig.questions.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.questions.mapper.ExamQuestionsAnswerMapper;
import com.hig.questions.domain.ExamQuestionsAnswer;
import com.hig.questions.service.IExamQuestionsAnswerService;

/**
 * 考试题目答案Service业务层处理
 * 
 * @author qnsdt
 * @date 2022-12-29
 */
@Service
public class ExamQuestionsAnswerServiceImpl implements IExamQuestionsAnswerService 
{
    @Autowired
    private ExamQuestionsAnswerMapper examQuestionsAnswerMapper;

    /**
     * 查询考试题目答案
     * 
     * @param questionsCode 考试题目答案ID
     * @return 考试题目答案
     */
    @Override
    public ExamQuestionsAnswer selectExamQuestionsAnswerById(String questionsCode)
    {
        return examQuestionsAnswerMapper.selectExamQuestionsAnswerById(questionsCode);
    }

    /**
     * 查询考试题目答案列表
     * 
     * @param examQuestionsAnswer 考试题目答案
     * @return 考试题目答案
     */
    @Override
    public List<ExamQuestionsAnswer> selectExamQuestionsAnswerList(ExamQuestionsAnswer examQuestionsAnswer)
    {
        return examQuestionsAnswerMapper.selectExamQuestionsAnswerList(examQuestionsAnswer);
    }

    /**
     * 新增考试题目答案
     * 
     * @param examQuestionsAnswer 考试题目答案
     * @return 结果
     */
    @Override
    public int insertExamQuestionsAnswer(ExamQuestionsAnswer examQuestionsAnswer)
    {
        return examQuestionsAnswerMapper.insertExamQuestionsAnswer(examQuestionsAnswer);
    }

    /**
     * 修改考试题目答案
     * 
     * @param examQuestionsAnswer 考试题目答案
     * @return 结果
     */
    @Override
    public int updateExamQuestionsAnswer(ExamQuestionsAnswer examQuestionsAnswer)
    {
        return examQuestionsAnswerMapper.updateExamQuestionsAnswer(examQuestionsAnswer);
    }

    /**
     * 批量删除考试题目答案
     * 
     * @param questionsCodes 需要删除的考试题目答案ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsAnswerByIds(String[] questionsCodes)
    {
        return examQuestionsAnswerMapper.deleteExamQuestionsAnswerByIds(questionsCodes);
    }

    /**
     * 删除考试题目答案信息
     * 
     * @param questionsCode 考试题目答案ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsAnswerById(String questionsCode)
    {
        return examQuestionsAnswerMapper.deleteExamQuestionsAnswerById(questionsCode);
    }

	@Override
	public List<ExamQuestionsAnswer> selectExamQuestionsOptionList(String questionsCode) {
		// TODO Auto-generated method stub
		return examQuestionsAnswerMapper.selectExamQuestionsOptionList(questionsCode);
	}
}
