package com.hig.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hig.onlineexam.domain.dto.ExamQuestionsListData;
import com.hig.onlineexam.mapper.ExamQuestionsListDataMapper;
import com.hig.onlineexam.service.IExamQuestionsListDataService;

@Service
public class ExamQuestionsListDataServiceImpl implements IExamQuestionsListDataService {
	@Autowired
	ExamQuestionsListDataMapper examQuestionsListDataMapper;
	
	@Override
	public List<ExamQuestionsListData> selectQuestionsList(ExamQuestionsListData examQuestionsListData) {
		// TODO Auto-generated method stub
		return examQuestionsListDataMapper.selectQuestionsList(examQuestionsListData);
	}

}
