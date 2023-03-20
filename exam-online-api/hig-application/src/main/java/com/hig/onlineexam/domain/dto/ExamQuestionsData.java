package com.hig.onlineexam.domain.dto;

import java.util.List;

import com.hig.questions.domain.ExamQuestionsAnswer;

import lombok.Data;

@Data
public class ExamQuestionsData {

	private ExamQuestionsContentData examQuestionsContentData;
	private List<ExamQuestionsAnswer> examQuestionsOptionList;
	
	
	public ExamQuestionsData(ExamQuestionsContentData examQuestionsContentData,
			List<ExamQuestionsAnswer> examQuestionsOptionList) {
		super();
		this.examQuestionsContentData = examQuestionsContentData;
		this.examQuestionsOptionList = examQuestionsOptionList;
	}
	
	
}
