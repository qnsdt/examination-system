package com.hig.onlineexam.domain.dto;

import lombok.Data;

@Data
public class ExamQuestionsListData {
	private String examCode;
	private String userCode;
    private String questionsCode;
    private int questionsNumber;
	private int questionsType;
	private int questionsScore;
    private String questionsAnswer;
    private int isMark;
    private int isCurrent;
}
