package com.hig.onlineexam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.onlineexam.mapper.ExamTaskAnswerMapper;
import com.hig.onlineexam.domain.ExamTaskAnswer;
import com.hig.onlineexam.domain.ExamUserScore;
import com.hig.onlineexam.service.IExamTaskAnswerService;
import com.hig.onlineexam.service.IExamUserScoreService;

/**
 * 考试实时数据Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
@Service
public class ExamTaskAnswerServiceImpl implements IExamTaskAnswerService 
{
    @Autowired
    private ExamTaskAnswerMapper examTaskAnswerMapper;


    /**
     * 查询考试实时数据
     * 
     * @param examCode 考试实时数据ID
     * @return 考试实时数据
     */
    @Override
    public ExamTaskAnswer selectExamTaskAnswerById(String examCode)
    {
        return examTaskAnswerMapper.selectExamTaskAnswerById(examCode);
    }

    /**
     * 查询考试实时数据列表
     * 
     * @param examTaskAnswer 考试实时数据
     * @return 考试实时数据
     */
    @Override
    public List<ExamTaskAnswer> selectExamTaskAnswerList(ExamTaskAnswer examTaskAnswer)
    {
        return examTaskAnswerMapper.selectExamTaskAnswerList(examTaskAnswer);
    }

    /**
     * 新增考试实时数据
     * 
     * @param examTaskAnswer 考试实时数据
     * @return 结果
     */
    @Override
    public int insertExamTaskAnswer(ExamTaskAnswer examTaskAnswer)
    {
    	// 安全删除
    	examTaskAnswerMapper.deleteExamTaskAnswer(examTaskAnswer);
    	
        return examTaskAnswerMapper.insertExamTaskAnswer(examTaskAnswer);
    }

    /**
     * 修改考试实时数据
     * 
     * @param examTaskAnswer 考试实时数据
     * @return 结果
     */
    @Override
    public int updateExamTaskAnswer(ExamTaskAnswer examTaskAnswer)
    {
        return examTaskAnswerMapper.updateExamTaskAnswer(examTaskAnswer);
    }

    /**
     * 批量删除考试实时数据
     * 
     * @param examCodes 需要删除的考试实时数据ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskAnswerByIds(String[] examCodes)
    {
        return examTaskAnswerMapper.deleteExamTaskAnswerByIds(examCodes);
    }

    /**
     * 删除考试实时数据信息
     * 
     * @param examCode 考试实时数据ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskAnswerById(String examCode)
    {
        return examTaskAnswerMapper.deleteExamTaskAnswerById(examCode);
    }

	@Override
	public int deleteExamTaskAnswer(ExamTaskAnswer examTaskAnswer) {
		// TODO Auto-generated method stub
		return examTaskAnswerMapper.deleteExamTaskAnswer(examTaskAnswer);
	}
}
