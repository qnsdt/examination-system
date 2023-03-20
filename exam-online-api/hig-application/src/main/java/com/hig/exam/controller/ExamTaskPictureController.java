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
import com.hig.exam.domain.ExamTaskPicture;
import com.hig.exam.service.IExamTaskPictureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试任务图片管理Controller
 * 
 * @author qnsdt
 * @date 2023-01-18
 */
@RestController
@RequestMapping("/exam/exampicture")
public class ExamTaskPictureController extends BaseController
{
    @Autowired
    private IExamTaskPictureService examTaskPictureService;

    /**
     * 查询考试任务图片管理列表
     */
    @PreAuthorize("@ss.hasPermi('exam:exampicture:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamTaskPicture examTaskPicture)
    {
        startPage();
        List<ExamTaskPicture> list = examTaskPictureService.selectExamTaskPictureList(examTaskPicture);
        return getDataTable(list);
    }

    /**
     * 导出考试任务图片管理列表
     */
    @PreAuthorize("@ss.hasPermi('exam:exampicture:export')")
    @Log(title = "考试任务图片管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamTaskPicture examTaskPicture)
    {
        List<ExamTaskPicture> list = examTaskPictureService.selectExamTaskPictureList(examTaskPicture);
        ExcelUtil<ExamTaskPicture> util = new ExcelUtil<ExamTaskPicture>(ExamTaskPicture.class);
        return util.exportExcel(list, "考试任务图片管理数据");
    }

    /**
     * 获取考试任务图片管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:exampicture:query')")
    @GetMapping(value = "/{photoCode}")
    public AjaxResult getInfo(@PathVariable("photoCode") String photoCode)
    {
        return AjaxResult.success(examTaskPictureService.selectExamTaskPictureById(photoCode));
    }

    /**
     * 新增考试任务图片管理
     */
    @PreAuthorize("@ss.hasPermi('exam:exampicture:add')")
    @Log(title = "考试任务图片管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamTaskPicture examTaskPicture)
    {
        return toAjax(examTaskPictureService.insertExamTaskPicture(examTaskPicture));
    }

    /**
     * 修改考试任务图片管理
     */
    @PreAuthorize("@ss.hasPermi('exam:exampicture:edit')")
    @Log(title = "考试任务图片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamTaskPicture examTaskPicture)
    {
        return toAjax(examTaskPictureService.updateExamTaskPicture(examTaskPicture));
    }

    /**
     * 删除考试任务图片管理
     */
    @PreAuthorize("@ss.hasPermi('exam:exampicture:remove')")
    @Log(title = "考试任务图片管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{photoCodes}")
    public AjaxResult remove(@PathVariable String[] photoCodes)
    {
        return toAjax(examTaskPictureService.deleteExamTaskPictureByIds(photoCodes));
    }
}
