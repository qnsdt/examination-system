package com.hig.system.controller;

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
import com.hig.system.domain.TempUserChoice;
import com.hig.system.service.ITempUserChoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户选择Controller
 * 
 * @author qnsdt
 * @date 2023-01-31
 */
@RestController
@RequestMapping("/system/userchoice")
public class TempUserChoiceController extends BaseController
{
    @Autowired
    private ITempUserChoiceService tempUserChoiceService;

    /**
     * 查询用户选择列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TempUserChoice tempUserChoice)
    {
        startPage();
        List<TempUserChoice> list = tempUserChoiceService.selectTempUserChoiceList(tempUserChoice);
        return getDataTable(list);
    }

    /**
     * 导出用户选择列表
     */
    @GetMapping("/export")
    public AjaxResult export(TempUserChoice tempUserChoice)
    {
        List<TempUserChoice> list = tempUserChoiceService.selectTempUserChoiceList(tempUserChoice);
        ExcelUtil<TempUserChoice> util = new ExcelUtil<TempUserChoice>(TempUserChoice.class);
        return util.exportExcel(list, "用户选择数据");
    }

    /**
     * 获取用户选择详细信息
     */
    @GetMapping(value = "/{choiceCode}")
    public AjaxResult getInfo(@PathVariable("choiceCode") String choiceCode)
    {
        return AjaxResult.success(tempUserChoiceService.selectTempUserChoiceById(choiceCode));
    }

    /**
     * 新增用户选择
     */
    @PostMapping
    public AjaxResult add(@RequestBody TempUserChoice tempUserChoice)
    {
        return toAjax(tempUserChoiceService.insertTempUserChoice(tempUserChoice));
    }
    
    /**
     * 新增用户选择
     */
    @PostMapping("/batch")
    public AjaxResult batchAdd(@RequestBody List<TempUserChoice> userList)
    {
        return toAjax(tempUserChoiceService.batchInsertTempUser(userList));
    }

    /**
     * 修改用户选择
     */
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody TempUserChoice tempUserChoice)
    {
        return toAjax(tempUserChoiceService.updateTempUserChoice(tempUserChoice));
    }

    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody List<TempUserChoice> userList)
    {
        return toAjax(tempUserChoiceService.deleteTempUserChoice(userList));
    }

}
