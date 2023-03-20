package com.hig.onlineexam.mapper;

import java.util.List;

import com.hig.onlineexam.domain.dto.ExamQuestionsListData;

public interface ExamQuestionsListDataMapper {
	public List<ExamQuestionsListData> selectQuestionsList(ExamQuestionsListData examQuestionsListData);
}
