package com.hig.onlineexam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hig.onlineexam.domain.dto.ExamTitleData;
import com.hig.onlineexam.service.IExamTitleDataService;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/onlineexam/examtitle")
public class ExamTitleController {
	
	@Autowired
	IExamTitleDataService examTitleDataService;

	@GetMapping(value = "/{userCode}")
    public AjaxResult selectCurrentExam(@PathVariable("userCode") String userCode)
    {
    	ExamTitleData examTitleData = examTitleDataService.selectCurrentExam(userCode);
        return AjaxResult.success(examTitleData);
    }
}
