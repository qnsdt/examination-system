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
import com.hig.onlineexam.domain.ExamFinishAnswer;
import com.hig.onlineexam.service.IExamFinishAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试成绩Controller
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
@RestController
@RequestMapping("/onlineexam/finishanswer")
public class ExamFinishAnswerController extends BaseController
{
    @Autowired
    private IExamFinishAnswerService examFinishAnswerService;

    /**
     * 查询考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:finishanswer:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamFinishAnswer examFinishAnswer)
    {
        startPage();
        List<ExamFinishAnswer> list = examFinishAnswerService.selectExamFinishAnswerList(examFinishAnswer);
        return getDataTable(list);
    }

    /**
     * 导出考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:finishanswer:export')")
    @Log(title = "考试成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamFinishAnswer examFinishAnswer)
    {
        List<ExamFinishAnswer> list = examFinishAnswerService.selectExamFinishAnswerList(examFinishAnswer);
        ExcelUtil<ExamFinishAnswer> util = new ExcelUtil<ExamFinishAnswer>(ExamFinishAnswer.class);
        return util.exportExcel(list, "考试成绩数据");
    }

    /**
     * 获取考试成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:finishanswer:query')")
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examFinishAnswerService.selectExamFinishAnswerById(examCode));
    }

    /**
     * 新增考试成绩
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:finishanswer:add')")
    @Log(title = "考试成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamFinishAnswer examFinishAnswer)
    {
        return toAjax(examFinishAnswerService.insertExamFinishAnswer(examFinishAnswer));
    }
    
    /**
     * 维护考试成绩
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ExamFinishAnswer examFinishAnswer)
    {
    	System.out.println("examFinishAnswer:" + examFinishAnswer.toString());
        return toAjax(examFinishAnswerService.intoExamFinishAnswer(examFinishAnswer));
    }

    /**
     * 修改考试成绩
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:finishanswer:edit')")
    @Log(title = "考试成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamFinishAnswer examFinishAnswer)
    {
        return toAjax(examFinishAnswerService.updateExamFinishAnswer(examFinishAnswer));
    }

    /**
     * 删除考试成绩
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:finishanswer:remove')")
    @Log(title = "考试成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examFinishAnswerService.deleteExamFinishAnswerByIds(examCodes));
    }
}
