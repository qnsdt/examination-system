package com.hig.onlineexam.controller;

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
import com.hig.onlineexam.domain.ExamTaskAnswer;
import com.hig.onlineexam.service.IExamTaskAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试实时数据Controller
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
@RestController
@RequestMapping("/onlineexam/taskanswer")
public class ExamTaskAnswerController extends BaseController
{
    @Autowired
    private IExamTaskAnswerService examTaskAnswerService;

    /**
     * 查询考试实时数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamTaskAnswer examTaskAnswer)
    {
        startPage();
        List<ExamTaskAnswer> list = examTaskAnswerService.selectExamTaskAnswerList(examTaskAnswer);
        return getDataTable(list);
    }

    /**
     * 导出考试实时数据列表
     */
    @GetMapping("/export")
    public AjaxResult export(ExamTaskAnswer examTaskAnswer)
    {
        List<ExamTaskAnswer> list = examTaskAnswerService.selectExamTaskAnswerList(examTaskAnswer);
        ExcelUtil<ExamTaskAnswer> util = new ExcelUtil<ExamTaskAnswer>(ExamTaskAnswer.class);
        return util.exportExcel(list, "考试实时数据数据");
    }

    /**
     * 获取考试实时数据详细信息
     */
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examTaskAnswerService.selectExamTaskAnswerById(examCode));
    }

    /**
     * 新增考试实时数据
     */
    @PostMapping
    public AjaxResult add(@RequestBody ExamTaskAnswer examTaskAnswer)
    {
    	
        return toAjax(examTaskAnswerService.insertExamTaskAnswer(examTaskAnswer));
    }

    /**
     * 修改考试实时数据
     */
    @PutMapping
    public AjaxResult edit(@RequestBody ExamTaskAnswer examTaskAnswer)
    {
        return toAjax(examTaskAnswerService.updateExamTaskAnswer(examTaskAnswer));
    }

    /**
     * 删除考试实时数据
     */
	@GetMapping("/remove/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examTaskAnswerService.deleteExamTaskAnswerByIds(examCodes));
    }
    
    /**
     * 删除考试实时数据
     */
	@PostMapping("/delete")
    public AjaxResult delete(ExamTaskAnswer examTaskAnswer)
    {
        return toAjax(examTaskAnswerService.deleteExamTaskAnswer(examTaskAnswer));
    }
}
