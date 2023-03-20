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
import com.hig.exam.domain.ExamTaskPerson;
import com.hig.exam.service.IExamTaskPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试人员Controller
 * 
 * @author qnsdt
 * @date 2023-01-06
 */
@RestController
@RequestMapping("/exam/taskperson")
public class ExamTaskPersonController extends BaseController
{
    @Autowired
    private IExamTaskPersonService examTaskPersonService;

    /**
     * 查询考试人员列表
     */
    @PreAuthorize("@ss.hasPermi('exam:taskperson:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamTaskPerson examTaskPerson)
    {
        startPage();
        List<ExamTaskPerson> list = examTaskPersonService.selectExamTaskPersonList(examTaskPerson);
        return getDataTable(list);
    }

    /**
     * 导出考试人员列表
     */
    @PreAuthorize("@ss.hasPermi('exam:taskperson:export')")
    @Log(title = "考试人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamTaskPerson examTaskPerson)
    {
        List<ExamTaskPerson> list = examTaskPersonService.selectExamTaskPersonList(examTaskPerson);
        ExcelUtil<ExamTaskPerson> util = new ExcelUtil<ExamTaskPerson>(ExamTaskPerson.class);
        return util.exportExcel(list, "考试人员数据");
    }

    /**
     * 获取考试人员详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:taskperson:query')")
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examTaskPersonService.selectExamTaskPersonById(examCode));
    }

    /**
     * 新增考试人员
     */
    @PreAuthorize("@ss.hasPermi('exam:taskperson:add')")
    @Log(title = "考试人员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ExamTaskPerson examTaskPerson)
    {
        return toAjax(examTaskPersonService.insertExamTaskPerson(examTaskPerson));
    }

    /**
     * 修改考试人员
     */
    @PreAuthorize("@ss.hasPermi('exam:taskperson:edit')")
    @Log(title = "考试人员", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamTaskPerson examTaskPerson)
    {
        return toAjax(examTaskPersonService.updateExamTaskPerson(examTaskPerson));
    }

    /**
     * 更新考试开始时间
     */
    @PostMapping("/starttime")
    public AjaxResult updateStartTime(@RequestBody ExamTaskPerson examTaskPerson)
    {
        return toAjax(examTaskPersonService.updateStartTime(examTaskPerson));
    }
    /**
     * 删除考试人员
     */
    @PreAuthorize("@ss.hasPermi('exam:taskperson:remove')")
    @Log(title = "考试人员", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examTaskPersonService.deleteExamTaskPersonByIds(examCodes));
    }
}
