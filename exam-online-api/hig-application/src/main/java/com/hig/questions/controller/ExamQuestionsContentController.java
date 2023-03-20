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
import com.hig.questions.domain.ExamQuestionsContent;
import com.hig.questions.service.IExamQuestionsContentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题目内容表Controller
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
@RestController
@RequestMapping("/questions/questionscontent")
public class ExamQuestionsContentController extends BaseController
{
    @Autowired
    private IExamQuestionsContentService examQuestionsContentService;

    /**
     * 查询题目内容表列表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionscontent:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestionsContent examQuestionsContent)
    {
        startPage();
        List<ExamQuestionsContent> list = examQuestionsContentService.selectExamQuestionsContentList(examQuestionsContent);
        return getDataTable(list);
    }

    /**
     * 导出题目内容表列表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionscontent:export')")
    @Log(title = "题目内容表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestionsContent examQuestionsContent)
    {
        List<ExamQuestionsContent> list = examQuestionsContentService.selectExamQuestionsContentList(examQuestionsContent);
        ExcelUtil<ExamQuestionsContent> util = new ExcelUtil<ExamQuestionsContent>(ExamQuestionsContent.class);
        return util.exportExcel(list, "题目内容表数据");
    }

    /**
     * 获取题目内容表详细信息
     */
    @GetMapping(value = "/{questionsCode}")
    public AjaxResult getInfo(@PathVariable("questionsCode") String questionsCode)
    {
        return AjaxResult.success(examQuestionsContentService.selectExamQuestionsContentById(questionsCode));
    }

    /**
     * 新增题目内容表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionscontent:add')")
    @Log(title = "题目内容表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamQuestionsContent examQuestionsContent)
    {
        return toAjax(examQuestionsContentService.insertExamQuestionsContent(examQuestionsContent));
    }

    /**
     * 修改题目内容表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionscontent:edit')")
    @Log(title = "题目内容表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamQuestionsContent examQuestionsContent)
    {
        return toAjax(examQuestionsContentService.updateExamQuestionsContent(examQuestionsContent));
    }

    /**
     * 删除题目内容表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionscontent:remove')")
    @Log(title = "题目内容表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{questionsCodes}")
    public AjaxResult remove(@PathVariable String[] questionsCodes)
    {
        return toAjax(examQuestionsContentService.deleteExamQuestionsContentByIds(questionsCodes));
    }
}
