package com.hig.onlineexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hig.onlineexam.domain.dto.ExamTitleData;
import com.hig.onlineexam.mapper.ExamTitleDataMapper;
import com.hig.onlineexam.service.IExamTitleDataService;

@Service
public class ExamTitleDataServiceImpl implements IExamTitleDataService {
	@Autowired
	ExamTitleDataMapper examTitleDataMapper;
	
	@Override
	public ExamTitleData selectCurrentExam(String userCode) {
		// TODO Auto-generated method stub
		return examTitleDataMapper.selectCurrentExam(userCode);
	}

}
