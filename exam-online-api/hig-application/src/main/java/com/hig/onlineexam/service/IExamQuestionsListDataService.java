package com.hig.onlineexam.service;

import java.util.List;

import com.hig.onlineexam.domain.dto.ExamQuestionsListData;

public interface IExamQuestionsListDataService {
	public List<ExamQuestionsListData> selectQuestionsList(ExamQuestionsListData examQuestionsListData);

}
