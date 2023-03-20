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
import com.hig.exam.domain.ExamTaskGroup;
import com.hig.exam.service.IExamTaskGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务分组Controller
 * 
 * @author qnsdt
 * @date 2023-02-10
 */
@RestController
@RequestMapping("/exam/taskgroup")
public class ExamTaskGroupController extends BaseController
{
    @Autowired
    private IExamTaskGroupService examTaskGroupService;

    /**
     * 查询任务分组列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamTaskGroup examTaskGroup)
    {
        startPage();
        List<ExamTaskGroup> list = examTaskGroupService.selectExamTaskGroupList(examTaskGroup);
        return getDataTable(list);
    }

    /**
     * 导出任务分组列表
     */
    @GetMapping("/export")
    public AjaxResult export(ExamTaskGroup examTaskGroup)
    {
        List<ExamTaskGroup> list = examTaskGroupService.selectExamTaskGroupList(examTaskGroup);
        ExcelUtil<ExamTaskGroup> util = new ExcelUtil<ExamTaskGroup>(ExamTaskGroup.class);
        return util.exportExcel(list, "任务分组数据");
    }

    /**
     * 获取任务分组详细信息
     */
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examTaskGroupService.selectExamTaskGroupById(examCode));
    }

    /**
     * 新增任务分组
     */
    @PostMapping
    public AjaxResult add(@RequestBody ExamTaskGroup examTaskGroup)
    {
        return toAjax(examTaskGroupService.insertExamTaskGroup(examTaskGroup));
    }

    /**
     * 修改任务分组
     */
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamTaskGroup examTaskGroup)
    {
        return toAjax(examTaskGroupService.updateExamTaskGroup(examTaskGroup));
    }

    @PostMapping("/batchupdate")
    public AjaxResult batchUpdate(@RequestBody List<ExamTaskGroup> examTaskGroupList)
    {
    	try
    	{
    		examTaskGroupService.batchUpdateExamTaskGroup(examTaskGroupList);
    	}
    	catch(Exception e) {
    		return AjaxResult.error(e.getMessage());
    	}
    	
        return AjaxResult.success();
    }
    /**
     * 删除任务分组
     */
    @GetMapping("/delete/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examTaskGroupService.deleteExamTaskGroupByIds(examCodes));
    }
}
