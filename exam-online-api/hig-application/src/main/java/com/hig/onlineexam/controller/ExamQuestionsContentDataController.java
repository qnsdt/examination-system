package com.hig.onlineexam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hig.onlineexam.domain.dto.ExamQuestionsContentData;
import com.hig.onlineexam.domain.dto.ExamQuestionsData;
import com.hig.onlineexam.service.IExamQuestionsContentDataService;
import com.hig.questions.domain.ExamQuestionsAnswer;
import com.hig.questions.service.IExamQuestionsAnswerService;
import com.hig.utils.DigitUtils;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/onlineexam")
public class ExamQuestionsContentDataController {
	@Autowired
	private IExamQuestionsContentDataService examQuestionsContentDataService;
	
    @Autowired
    private IExamQuestionsAnswerService examQuestionsAnswerService;
	
    @GetMapping("/content")
    public AjaxResult option(@RequestBody ExamQuestionsContentData examQuestionsContentData)
    {
        ExamQuestionsContentData contentData = examQuestionsContentDataService.selectExamQuestionsContent(examQuestionsContentData);
        return AjaxResult.success(contentData);
    }
    
    @PostMapping("/data")
    public AjaxResult data(@RequestBody ExamQuestionsContentData examQuestionsContentData)
    {
    	ExamQuestionsContentData contentData = examQuestionsContentDataService.selectExamQuestionsContent(examQuestionsContentData);
    	
    	// 转换题号
    	
    	String str = DigitUtils.toChinese(String.valueOf(contentData.getQuestionsNumber()));
    	contentData.setQuestionsNumberText("第" + str + "题");
    	
    	List<ExamQuestionsAnswer> examQuestionsOptionList = examQuestionsAnswerService.selectExamQuestionsOptionList(examQuestionsContentData.getQuestionsCode());
        
    	ExamQuestionsData examQuestionsData = new ExamQuestionsData(contentData, examQuestionsOptionList);
    	return AjaxResult.success(examQuestionsData);
    }

}
