package com.hig.onlineexam.service;

import com.hig.onlineexam.domain.dto.ExamTitleData;

public interface IExamTitleDataService {
	public ExamTitleData selectCurrentExam(String userCode);
}
