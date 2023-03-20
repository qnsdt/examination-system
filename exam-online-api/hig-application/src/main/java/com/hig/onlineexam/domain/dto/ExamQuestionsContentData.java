package com.hig.onlineexam.domain.dto;

import lombok.Data;

@Data
public class ExamQuestionsContentData {
   private String bankCode;
   private String examCode;
   private int questionsId;
   private int questionsNumber;
   private String questionsNumberText;
   private String questionsCode;
   private String questionsContent;
   private int questionsType;
   private int questionsScore;
   private int rateNumber;
}
