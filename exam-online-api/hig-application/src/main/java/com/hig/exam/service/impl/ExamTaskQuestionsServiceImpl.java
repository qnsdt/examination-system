package com.hig.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.exam.mapper.ExamTaskQuestionsMapper;
import com.hig.exam.domain.ExamTaskQuestions;
import com.hig.exam.service.IExamTaskQuestionsService;

/**
 * 任务题目Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
@Service
public class ExamTaskQuestionsServiceImpl implements IExamTaskQuestionsService 
{
    @Autowired
    private ExamTaskQuestionsMapper examTaskQuestionsMapper;

    /**
     * 查询任务题目
     * 
     * @param examCode 任务题目ID
     * @return 任务题目
     */
    @Override
    public ExamTaskQuestions selectExamTaskQuestionsById(String examCode)
    {
        return examTaskQuestionsMapper.selectExamTaskQuestionsById(examCode);
    }

    /**
     * 查询任务题目列表
     * 
     * @param examTaskQuestions 任务题目
     * @return 任务题目
     */
    @Override
    public List<ExamTaskQuestions> selectExamTaskQuestionsList(ExamTaskQuestions examTaskQuestions)
    {
        return examTaskQuestionsMapper.selectExamTaskQuestionsList(examTaskQuestions);
    }

    /**
     * 新增任务题目
     * 
     * @param examTaskQuestions 任务题目
     * @return 结果
     */
    @Override
    public int insertExamTaskQuestions(ExamTaskQuestions examTaskQuestions)
    {
        return examTaskQuestionsMapper.insertExamTaskQuestions(examTaskQuestions);
    }

    /**
     * 修改任务题目
     * 
     * @param examTaskQuestions 任务题目
     * @return 结果
     */
    @Override
    public int updateExamTaskQuestions(ExamTaskQuestions examTaskQuestions)
    {
        return examTaskQuestionsMapper.updateExamTaskQuestions(examTaskQuestions);
    }

    /**
     * 批量删除任务题目
     * 
     * @param examCodes 需要删除的任务题目ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskQuestionsByIds(String[] examCodes)
    {
        return examTaskQuestionsMapper.deleteExamTaskQuestionsByIds(examCodes);
    }

    /**
     * 删除任务题目信息
     * 
     * @param examCode 任务题目ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskQuestionsById(String examCode)
    {
        return examTaskQuestionsMapper.deleteExamTaskQuestionsById(examCode);
    }

	@Override
	public int batchUpdateExamTaskQuestions(List<ExamTaskQuestions> examTaskQuestionsList) throws Exception {
		// TODO Auto-generated method stub
		// 安全删除
		
		examTaskQuestionsMapper.deleteExamTaskQuestionsById(examTaskQuestionsList.get(0).getExamCode());
		
		for(ExamTaskQuestions examTaskQuestions: examTaskQuestionsList) {
			try
			{
				examTaskQuestionsMapper.insertExamTaskQuestions(examTaskQuestions);
			}
			catch(Exception e) {
				throw new Exception("保存题目信息出错,信息:" + e.getMessage());				
			}
			
		}
		return 1;
	}
}
