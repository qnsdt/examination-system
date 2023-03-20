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
import com.hig.onlineexam.domain.ExamUserScore;
import com.hig.onlineexam.service.IExamUserScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试成绩Controller
 * 
 * @author qnsdt
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/onlineexam/userscore")
public class ExamUserScoreController extends BaseController
{
    @Autowired
    private IExamUserScoreService examUserScoreService;

    /**
     * 查询考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:userscore:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamUserScore examUserScore)
    {
        startPage();
        List<ExamUserScore> list = examUserScoreService.selectExamUserScoreList(examUserScore);
        return getDataTable(list);
    }

    /**
     * 导出考试成绩列表
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:userscore:export')")
    @Log(title = "考试成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamUserScore examUserScore)
    {
        List<ExamUserScore> list = examUserScoreService.selectExamUserScoreList(examUserScore);
        ExcelUtil<ExamUserScore> util = new ExcelUtil<ExamUserScore>(ExamUserScore.class);
        return util.exportExcel(list, "考试成绩数据");
    }

    /**
     * 获取考试成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:userscore:query')")
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examUserScoreService.selectExamUserScoreById(examCode));
    }

    /**
     * 新增考试成绩
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:userscore:add')")
    @Log(title = "考试成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamUserScore examUserScore)
    {
        return toAjax(examUserScoreService.insertExamUserScore(examUserScore));
    }
    
    /**
     * 维护考试成绩
     */
     @PostMapping("/into")
    public AjaxResult into(@RequestBody ExamUserScore examUserScore)
    {
        return toAjax(examUserScoreService.intoExamUserScore(examUserScore));
    }

    /**
     * 修改考试成绩
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:userscore:edit')")
    @Log(title = "考试成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamUserScore examUserScore)
    {
        return toAjax(examUserScoreService.updateExamUserScore(examUserScore));
    }

    /**
     * 删除考试成绩
     */
    @PreAuthorize("@ss.hasPermi('onlineexam:userscore:remove')")
    @Log(title = "考试成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examUserScoreService.deleteExamUserScoreByIds(examCodes));
    }
}
