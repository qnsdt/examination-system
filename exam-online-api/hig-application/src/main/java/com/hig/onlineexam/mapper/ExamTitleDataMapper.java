package com.hig.onlineexam.mapper;

import com.hig.onlineexam.domain.dto.ExamTitleData;

public interface ExamTitleDataMapper {
	public ExamTitleData selectCurrentExam(String userCode);
}
