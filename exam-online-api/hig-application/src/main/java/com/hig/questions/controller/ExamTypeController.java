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
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.hig.questions.domain.ExamType;
import com.hig.questions.service.IExamTypeService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;

/**
 * 考试分类Controller
 * 
 * @author qnsdt
 * @date 2022-12-14
 */
@RestController
@RequestMapping("/questions/examtype")
public class ExamTypeController extends BaseController
{
    @Autowired
    private IExamTypeService examTypeService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询考试分类列表
     */
    @PreAuthorize("@ss.hasPermi('questions:examtype:list')")
    @GetMapping("/list")
    public AjaxResult list(ExamType examType)
    {
        List<ExamType> list = examTypeService.selectExamTypeList(examType);
        return AjaxResult.success(list);
    }

    /**
     * 导出考试分类列表
     */
    @PreAuthorize("@ss.hasPermi('questions:examtype:export')")
    @Log(title = "考试分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamType examType)
    {
        List<ExamType> list = examTypeService.selectExamTypeList(examType);
        ExcelUtil<ExamType> util = new ExcelUtil<ExamType>(ExamType.class);
        return util.exportExcel(list, "考试分类数据");
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/typetreeselect")
    public AjaxResult treeselect(ExamType examType)
    {
    	List<ExamType> list = examTypeService.selectExamTypeList(examType);    	
        return AjaxResult.success(examTypeService.buildTypeTreeSelect(list));
    }
    /**
     * 获取考试分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('questions:examtype:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(examTypeService.selectExamTypeById(typeId));
    }

    /**
     * 新增考试分类
     */
    @PreAuthorize("@ss.hasPermi('questions:examtype:add')")
    @Log(title = "考试分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamType examType)
    {
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    	// System.out.println("loginUser:" + loginUser.toString());
    	examType.setCreateBy(loginUser.getUser().getUserName());    	
        return toAjax(examTypeService.insertExamType(examType));
    }

    /**
     * 修改考试分类
     */
    @PreAuthorize("@ss.hasPermi('questions:examtype:edit')")
    @Log(title = "考试分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamType examType)
    {
        return toAjax(examTypeService.updateExamType(examType));
    }

    /**
     * 删除考试分类
     */
    @PreAuthorize("@ss.hasPermi('questions:examtype:remove')")
    @Log(title = "考试分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(examTypeService.deleteExamTypeByIds(typeIds));
    }
}
