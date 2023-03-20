package com.hig.onlineexam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hig.onlineexam.domain.dto.ExamQuestionsListData;
import com.hig.onlineexam.service.IExamQuestionsListDataService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/onlineexam/questionslist")
public class ExamQuestionsListDataController {
	
	@Autowired
	IExamQuestionsListDataService examQuestionsListDataService;
	
	@GetMapping()
    public AjaxResult getQuestionsList(ExamQuestionsListData examQuestionsListData)
    {
		List<ExamQuestionsListData> examQuestionsList = examQuestionsListDataService.selectQuestionsList(examQuestionsListData);
        return AjaxResult.success(examQuestionsList);
    }
}
