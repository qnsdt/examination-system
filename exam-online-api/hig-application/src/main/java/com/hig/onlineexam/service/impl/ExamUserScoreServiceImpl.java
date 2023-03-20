package com.hig.onlineexam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.onlineexam.mapper.ExamUserScoreMapper;
import com.hig.onlineexam.domain.ExamUserScore;
import com.hig.onlineexam.service.IExamUserScoreService;

/**
 * 考试成绩Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-03-03
 */
@Service
public class ExamUserScoreServiceImpl implements IExamUserScoreService 
{
    @Autowired
    private ExamUserScoreMapper examUserScoreMapper;

    /**
     * 查询考试成绩
     * 
     * @param examCode 考试成绩ID
     * @return 考试成绩
     */
    @Override
    public ExamUserScore selectExamUserScoreById(String examCode)
    {
        return examUserScoreMapper.selectExamUserScoreById(examCode);
    }

    /**
     * 查询考试成绩列表
     * 
     * @param examUserScore 考试成绩
     * @return 考试成绩
     */
    @Override
    public List<ExamUserScore> selectExamUserScoreList(ExamUserScore examUserScore)
    {
        return examUserScoreMapper.selectExamUserScoreList(examUserScore);
    }

    /**
     * 新增考试成绩
     * 
     * @param examUserScore 考试成绩
     * @return 结果
     */
    @Override
    public int insertExamUserScore(ExamUserScore examUserScore)
    {
        return examUserScoreMapper.insertExamUserScore(examUserScore);
    }

    /**
     * 修改考试成绩
     * 
     * @param examUserScore 考试成绩
     * @return 结果
     */
    @Override
    public int updateExamUserScore(ExamUserScore examUserScore)
    {
        return examUserScoreMapper.updateExamUserScore(examUserScore);
    }

    /**
     * 批量删除考试成绩
     * 
     * @param examCodes 需要删除的考试成绩ID
     * @return 结果
     */
    @Override
    public int deleteExamUserScoreByIds(String[] examCodes)
    {
        return examUserScoreMapper.deleteExamUserScoreByIds(examCodes);
    }

    /**
     * 删除考试成绩信息
     * 
     * @param examCode 考试成绩ID
     * @return 结果
     */
    @Override
    public int deleteExamUserScoreById(String examCode)
    {
        return examUserScoreMapper.deleteExamUserScoreById(examCode);
    }

	@Override
	public int intoExamUserScore(ExamUserScore examUserScore) {
		// TODO Auto-generated method stub
		// 安全删除
		examUserScoreMapper.deleteExamUserScore(examUserScore);
		return examUserScoreMapper.intoExamUserScore(examUserScore);
	}

	@Override
	public int deleteExamUserScore(ExamUserScore examUserScore) {
		// TODO Auto-generated method stub
		return examUserScoreMapper.deleteExamUserScore(examUserScore);
	}
}
