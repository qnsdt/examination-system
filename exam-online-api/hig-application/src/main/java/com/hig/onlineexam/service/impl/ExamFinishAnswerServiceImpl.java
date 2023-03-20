package com.hig.onlineexam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.onlineexam.mapper.ExamFinishAnswerMapper;
import com.hig.exam.domain.ExamTaskPerson;
import com.hig.exam.service.IExamTaskPersonService;
import com.hig.onlineexam.domain.ExamFinishAnswer;
import com.hig.onlineexam.domain.ExamUserScore;
import com.hig.onlineexam.service.IExamFinishAnswerService;
import com.hig.onlineexam.service.IExamUserScoreService;

/**
 * 考试成绩Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
@Service
public class ExamFinishAnswerServiceImpl implements IExamFinishAnswerService 
{
    @Autowired
    private ExamFinishAnswerMapper examFinishAnswerMapper;

    @Autowired
    private IExamUserScoreService examUserScoreService;
    
    @Autowired
    private IExamTaskPersonService examTaskPersonService;


    /**
     * 查询考试成绩
     * 
     * @param examCode 考试成绩ID
     * @return 考试成绩
     */
    @Override
    public ExamFinishAnswer selectExamFinishAnswerById(String examCode)
    {
        return examFinishAnswerMapper.selectExamFinishAnswerById(examCode);
    }

    /**
     * 查询考试成绩列表
     * 
     * @param examFinishAnswer 考试成绩
     * @return 考试成绩
     */
    @Override
    public List<ExamFinishAnswer> selectExamFinishAnswerList(ExamFinishAnswer examFinishAnswer)
    {
        return examFinishAnswerMapper.selectExamFinishAnswerList(examFinishAnswer);
    }

    /**
     * 新增考试成绩
     * 
     * @param examFinishAnswer 考试成绩
     * @return 结果
     */
    @Override
    public int insertExamFinishAnswer(ExamFinishAnswer examFinishAnswer)
    {
        return examFinishAnswerMapper.insertExamFinishAnswer(examFinishAnswer);
    }

    /**
     * 修改考试成绩
     * 
     * @param examFinishAnswer 考试成绩
     * @return 结果
     */
    @Override
    public int updateExamFinishAnswer(ExamFinishAnswer examFinishAnswer)
    {
        return examFinishAnswerMapper.updateExamFinishAnswer(examFinishAnswer);
    }

    /**
     * 批量删除考试成绩
     * 
     * @param examCodes 需要删除的考试成绩ID
     * @return 结果
     */
    @Override
    public int deleteExamFinishAnswerByIds(String[] examCodes)
    {
        return examFinishAnswerMapper.deleteExamFinishAnswerByIds(examCodes);
    }

    /**
     * 删除考试成绩信息
     * 
     * @param examCode 考试成绩ID
     * @return 结果
     */
    @Override
    public int deleteExamFinishAnswerById(String examCode)
    {
        return examFinishAnswerMapper.deleteExamFinishAnswerById(examCode);
    }

	@Override
	public int intoExamFinishAnswer(ExamFinishAnswer examFinishAnswer) {
		// TODO Auto-generated method stub
		examFinishAnswerMapper.deleteExamFinishAnswer(examFinishAnswer);
		
		int result = examFinishAnswerMapper.intoExamFinishAnswer(examFinishAnswer);
		
		// 维护成绩数据
    	ExamUserScore examUserScore = new ExamUserScore();
    	examUserScore.setExamCode(examFinishAnswer.getExamCode());
    	examUserScore.setUserCode(examFinishAnswer.getUserCode());
    	
    	result = examUserScoreService.intoExamUserScore(examUserScore);
    	
    	// 更新状态
    	ExamTaskPerson examTaskPerson = new ExamTaskPerson();
    	examTaskPerson.setExamCode(examFinishAnswer.getExamCode());
    	examTaskPerson.setuserCode(examFinishAnswer.getUserCode());
    	examTaskPersonService.updateDoneStatus(examTaskPerson);
    	return result;		 
	}

	@Override
	public int deleteExamFinishAnswer(ExamFinishAnswer examFinishAnswer) {
		// TODO Auto-generated method stub
		return examFinishAnswerMapper.deleteExamFinishAnswer(examFinishAnswer);
	}
}
