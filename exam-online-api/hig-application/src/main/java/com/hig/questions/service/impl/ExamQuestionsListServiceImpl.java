package com.hig.questions.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hig.questions.mapper.ExamQuestionsListMapper;
import com.hig.questions.mapper.ExamQuestionsPropertyMapper;
import com.hig.questions.domain.ExamQuestionsProperty;
import com.hig.questions.service.IExamQuestionsListService;
import com.hig.questions.service.IExamQuestionsPropertyService;

/**
 * 题目属性Service业务层处理
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
@Service
public class ExamQuestionsListServiceImpl implements IExamQuestionsListService 
{
    @Autowired
    private ExamQuestionsListMapper examQuestionsListMapper;


    /**
     * 查询题目属性列表
     * 
     * @param examQuestionsProperty 题目属性
     * @return 题目属性
     */
    @Override
    public List<ExamQuestionsProperty> selectExamQuestionsList(String[] bankCodes)
    {
        return examQuestionsListMapper.selectExamQuestionsList(bankCodes);
    }


	@Override
	public List<ExamQuestionsProperty> selectChoiceExamQuestionsList(String examCode) {
		// TODO Auto-generated method stub
		return examQuestionsListMapper.selectChoiceExamQuestionsList(examCode);
	}




}
