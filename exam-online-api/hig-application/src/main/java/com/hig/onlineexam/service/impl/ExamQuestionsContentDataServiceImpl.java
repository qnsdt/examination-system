package com.hig.onlineexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hig.onlineexam.domain.dto.ExamQuestionsContentData;
import com.hig.onlineexam.mapper.ExamQuestionsContentDataMapper;
import com.hig.onlineexam.service.IExamQuestionsContentDataService;

@Service
public class ExamQuestionsContentDataServiceImpl implements IExamQuestionsContentDataService {
	@Autowired
	ExamQuestionsContentDataMapper examQuestionsContentDataMapper;
	
	
	@Override
	public ExamQuestionsContentData selectExamQuestionsContent(ExamQuestionsContentData questionsContentData) {
		// TODO Auto-generated method stub
		return examQuestionsContentDataMapper.selectExamQuestionsContent(questionsContentData);
	}

}
