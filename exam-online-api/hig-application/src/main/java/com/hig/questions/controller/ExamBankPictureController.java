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
import com.hig.questions.domain.ExamBankPicture;
import com.hig.questions.service.IExamBankPictureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题库图片Controller
 * 
 * @author qnsdt
 * @date 2022-12-22
 */
@RestController
@RequestMapping("/questions/bankpicture")
public class ExamBankPictureController extends BaseController
{
    @Autowired
    private IExamBankPictureService examBankPictureService;

    /**
     * 查询题库图片列表
     */
    @PreAuthorize("@ss.hasPermi('questions:bankpicture:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamBankPicture examBankPicture)
    {
        startPage();
        List<ExamBankPicture> list = examBankPictureService.selectExamBankPictureList(examBankPicture);
        return getDataTable(list);
    }

    /**
     * 导出题库图片列表
     */
    @PreAuthorize("@ss.hasPermi('questions:bankpicture:export')")
    @Log(title = "题库图片", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamBankPicture examBankPicture)
    {
        List<ExamBankPicture> list = examBankPictureService.selectExamBankPictureList(examBankPicture);
        ExcelUtil<ExamBankPicture> util = new ExcelUtil<ExamBankPicture>(ExamBankPicture.class);
        return util.exportExcel(list, "题库图片数据");
    }

    /**
     * 获取题库图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('questions:bankpicture:query')")
    @GetMapping(value = "/{photoCode}")
    public AjaxResult getInfo(@PathVariable("photoCode") String photoCode)
    {
        return AjaxResult.success(examBankPictureService.selectExamBankPictureById(photoCode));
    }

    /**
     * 新增题库图片
     */
    @PreAuthorize("@ss.hasPermi('questions:bankpicture:add')")
    @Log(title = "题库图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamBankPicture examBankPicture)
    {
        return toAjax(examBankPictureService.insertExamBankPicture(examBankPicture));
    }

    /**
     * 修改题库图片
     */
    @PreAuthorize("@ss.hasPermi('questions:bankpicture:edit')")
    @Log(title = "题库图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamBankPicture examBankPicture)
    {
        return toAjax(examBankPictureService.updateExamBankPicture(examBankPicture));
    }

    /**
     * 删除题库图片
     */
    @PreAuthorize("@ss.hasPermi('questions:bankpicture:remove')")
    @Log(title = "题库图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{photoCodes}")
    public AjaxResult remove(@PathVariable String[] photoCodes)
    {
        return toAjax(examBankPictureService.deleteExamBankPictureByIds(photoCodes));
    }
}
