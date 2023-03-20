package com.hig.questions.controller;

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
import com.hig.questions.domain.ExamQuestionsAnswer;
import com.hig.questions.service.IExamQuestionsAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试题目答案Controller
 * 
 * @author qnsdt
 * @date 2022-12-29
 */
@RestController
@RequestMapping("/questions/answer")
public class ExamQuestionsAnswerController extends BaseController
{
    @Autowired
    private IExamQuestionsAnswerService examQuestionsAnswerService;

    /**
     * 查询考试题目答案列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestionsAnswer examQuestionsAnswer)
    {
        startPage();
        List<ExamQuestionsAnswer> list = examQuestionsAnswerService.selectExamQuestionsAnswerList(examQuestionsAnswer);
        return getDataTable(list);
    }

    @GetMapping("/optionlist/{questionsCode}")
    public AjaxResult optionList(@PathVariable("questionsCode") String questionsCode)
    {
        List<ExamQuestionsAnswer> list = examQuestionsAnswerService.selectExamQuestionsOptionList(questionsCode);
        return AjaxResult.success(list);
    }

    /**
     * 导出考试题目答案列表
     */
    @PreAuthorize("@ss.hasPermi('questions:answer:export')")
    @Log(title = "考试题目答案", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestionsAnswer examQuestionsAnswer)
    {
        List<ExamQuestionsAnswer> list = examQuestionsAnswerService.selectExamQuestionsAnswerList(examQuestionsAnswer);
        ExcelUtil<ExamQuestionsAnswer> util = new ExcelUtil<ExamQuestionsAnswer>(ExamQuestionsAnswer.class);
        return util.exportExcel(list, "考试题目答案数据");
    }

    /**
     * 获取考试题目答案详细信息
     */
    @PreAuthorize("@ss.hasPermi('questions:answer:query')")
    @GetMapping(value = "/{questionsCode}")
    public AjaxResult getInfo(@PathVariable("questionsCode") String questionsCode)
    {
        return AjaxResult.success(examQuestionsAnswerService.selectExamQuestionsAnswerById(questionsCode));
    }

    /**
     * 新增考试题目答案
     */
    @PreAuthorize("@ss.hasPermi('questions:answer:add')")
    @Log(title = "考试题目答案", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ExamQuestionsAnswer examQuestionsAnswer)
    {
        return toAjax(examQuestionsAnswerService.insertExamQuestionsAnswer(examQuestionsAnswer));
    }

    /**
     * 修改考试题目答案
     */
    @PreAuthorize("@ss.hasPermi('questions:answer:edit')")
    @Log(title = "考试题目答案", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamQuestionsAnswer examQuestionsAnswer)
    {
        return toAjax(examQuestionsAnswerService.updateExamQuestionsAnswer(examQuestionsAnswer));
    }

    /**
     * 删除考试题目答案
     */
    @PreAuthorize("@ss.hasPermi('questions:answer:remove')")
    @Log(title = "考试题目答案", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{questionsCodes}")
    public AjaxResult remove(@PathVariable String[] questionsCodes)
    {
        return toAjax(examQuestionsAnswerService.deleteExamQuestionsAnswerByIds(questionsCodes));
    }
}
