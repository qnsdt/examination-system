package com.hig.exam.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.hig.exam.domain.ExamTaskQuestions;
import com.hig.exam.service.IExamTaskQuestionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务题目Controller
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
@RestController
@RequestMapping("/exam/taskquestions")
public class ExamTaskQuestionsController extends BaseController
{
    @Autowired
    private IExamTaskQuestionsService examTaskQuestionsService;

    /**
     * 查询任务题目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamTaskQuestions examTaskQuestions)
    {
        startPage();
        List<ExamTaskQuestions> list = examTaskQuestionsService.selectExamTaskQuestionsList(examTaskQuestions);
        return getDataTable(list);
    }

    /**
     * 导出任务题目列表
     */
    @GetMapping("/export")
    public AjaxResult export(ExamTaskQuestions examTaskQuestions)
    {
        List<ExamTaskQuestions> list = examTaskQuestionsService.selectExamTaskQuestionsList(examTaskQuestions);
        ExcelUtil<ExamTaskQuestions> util = new ExcelUtil<ExamTaskQuestions>(ExamTaskQuestions.class);
        return util.exportExcel(list, "任务题目数据");
    }

    /**
     * 获取任务题目详细信息
     */
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examTaskQuestionsService.selectExamTaskQuestionsById(examCode));
    }


    @PostMapping("/batchupdate")
    public AjaxResult batchUpdate(@RequestBody List<ExamTaskQuestions> examTaskQuestionsList)
    {
    	int count = -1;
    	
    	try
    	{
    		count = examTaskQuestionsService.batchUpdateExamTaskQuestions(examTaskQuestionsList);
    	}
    	catch(Exception e) {
    		return AjaxResult.error(e.getMessage());
    	}
    	return AjaxResult.success("更新成功");
    }

    /**
     * 修改任务题目
     */
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamTaskQuestions examTaskQuestions)
    {
        return toAjax(examTaskQuestionsService.updateExamTaskQuestions(examTaskQuestions));
    }

    /**
     * 删除任务题目
     */
    @GetMapping("/delete/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examTaskQuestionsService.deleteExamTaskQuestionsByIds(examCodes));
    }
}
