package com.hig.exam.domain;

import java.util.List;

import lombok.Data;

@Data
public class ExamTaskData {
	private ExamTaskManager examTaskManager;
	private List<ExamTaskGroup> taskGroupList;
	private List<ExamTaskQuestions> taskQuestionsList;
	
}
