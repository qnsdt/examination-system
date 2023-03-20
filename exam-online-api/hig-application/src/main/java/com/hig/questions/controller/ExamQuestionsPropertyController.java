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
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.hig.questions.domain.ExamQuestionsProperty;
import com.hig.questions.service.IExamQuestionsPropertyService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题目属性Controller
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
@RestController
@RequestMapping("/questions/questionsproperty")
public class ExamQuestionsPropertyController extends BaseController
{
    @Autowired
    private IExamQuestionsPropertyService examQuestionsPropertyService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询题目属性列表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsproperty:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestionsProperty examQuestionsProperty)
    {
        startPage();
        List<ExamQuestionsProperty> list = examQuestionsPropertyService.selectExamQuestionsPropertyList(examQuestionsProperty);
        return getDataTable(list);
    }

    /**
     * 导出题目属性列表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsproperty:export')")
    @Log(title = "题目属性", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestionsProperty examQuestionsProperty)
    {
        List<ExamQuestionsProperty> list = examQuestionsPropertyService.selectExamQuestionsPropertyList(examQuestionsProperty);
        ExcelUtil<ExamQuestionsProperty> util = new ExcelUtil<ExamQuestionsProperty>(ExamQuestionsProperty.class);
        return util.exportExcel(list, "题目属性数据");
    }

    /**
     * 获取题目属性详细信息
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsproperty:query')")
    @GetMapping(value = "/{questionsCode}")
    public AjaxResult getInfo(@PathVariable("questionsCode") String questionsCode)
    {
        return AjaxResult.success(examQuestionsPropertyService.selectExamQuestionsPropertyById(questionsCode));
    }

    /**
     * 新增题目属性
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsproperty:add')")
    @Log(title = "题目属性", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ExamQuestionsProperty examQuestionsProperty)
    {
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    	examQuestionsProperty.setCreateBy(loginUser.getUser().getUserName());
    	examQuestionsProperty.setCreateDept(loginUser.getUser().getDept().getDeptId());

        return toAjax(examQuestionsPropertyService.insertExamQuestionsProperty(examQuestionsProperty));
    }

    /**
     * 修改题目属性
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsproperty:edit')")
    @Log(title = "题目属性", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamQuestionsProperty examQuestionsProperty)
    {
        return toAjax(examQuestionsPropertyService.updateExamQuestionsProperty(examQuestionsProperty));
    }

    /**
     * 删除题目属性
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsproperty:remove')")
    @Log(title = "题目属性", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{questionsCodes}")
    public AjaxResult remove(@PathVariable String[] questionsCodes)
    {
        return toAjax(examQuestionsPropertyService.deleteExamQuestionsPropertyByIds(questionsCodes));
    }
}
