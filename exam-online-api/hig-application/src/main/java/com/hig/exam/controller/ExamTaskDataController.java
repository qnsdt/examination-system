package com.hig.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hig.exam.domain.ExamTaskData;
import com.hig.exam.service.IExamTaskDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

@RestController
@RequestMapping("/exam/examtask")
public class ExamTaskDataController  extends BaseController{
    @Autowired
    private IExamTaskDataService examTaskDataService;

	
	@PostMapping("/save")
    public AjaxResult save(@RequestBody ExamTaskData examTaskData)
    {
		// 数据判断
		if (examTaskData == null) {
			return AjaxResult.error(-1, "传入数据为空!");
		}
		
		if (examTaskData.getExamTaskManager() == null) {
			return AjaxResult.error(-1, "传入任务属性信息为空!");
		}

		if (examTaskData.getTaskQuestionsList() == null || examTaskData.getTaskQuestionsList().size() == 0) {
			return AjaxResult.error(-1, "传入题库信息为空!");
		}
		
		if (examTaskData.getTaskGroupList() == null || examTaskData.getTaskGroupList().size() == 0) {
			return AjaxResult.error(-1, "传入人员分组信息为空!");
		}
		
		try
		{
			examTaskDataService.saveData(examTaskData);
		}
		catch(Exception e) {
			return AjaxResult.error(-1, e.getMessage());	
		}
		return AjaxResult.success("保存成功");
    }
}
