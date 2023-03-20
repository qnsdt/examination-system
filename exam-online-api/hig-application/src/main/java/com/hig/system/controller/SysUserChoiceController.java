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
import com.hig.system.domain.SysUserChoice;
import com.hig.system.domain.dto.SysUserCopy;
import com.hig.system.domain.dto.SysUserData;
import com.hig.system.service.ISysUserChoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 选择用户Controller
 * 
 * @author qnsdt
 * @date 2023-02-02
 */
@RestController
@RequestMapping("/system/sysuserchoice")
public class SysUserChoiceController extends BaseController
{
    @Autowired
    private ISysUserChoiceService sysUserChoiceService;

    /**
     * 查询选择用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUserChoice sysUserChoice)
    {
        startPage();
        List<SysUserChoice> list = sysUserChoiceService.selectSysUserChoiceList(sysUserChoice);
        return getDataTable(list);
    }
    
    @GetMapping("/choiceuserlist")
    public AjaxResult selectGroupUserList(SysUserData sysUser)
    {
    	System.out.println("SysUserData:" + sysUser.toString());
        List<SysUserData> list = sysUserChoiceService.selectGroupUserList(sysUser);
        return AjaxResult.success(list);

    }

    /**
     * 导出选择用户列表
     */
    @GetMapping("/export")
    public AjaxResult export(SysUserChoice sysUserChoice)
    {
        List<SysUserChoice> list = sysUserChoiceService.selectSysUserChoiceList(sysUserChoice);
        ExcelUtil<SysUserChoice> util = new ExcelUtil<SysUserChoice>(SysUserChoice.class);
        return util.exportExcel(list, "选择用户数据");
    }

    /**
     * 获取选择用户详细信息
     */
    @GetMapping(value = "/{choiceCode}")
    public AjaxResult getInfo(@PathVariable("choiceCode") String choiceCode)
    {
        return AjaxResult.success(sysUserChoiceService.selectSysUserChoiceById(choiceCode));
    }

    /**
     * 新增选择用户
     */
    @PostMapping("/copysysuser")
    public AjaxResult copySysUser(@RequestBody SysUserCopy sysUserCopy)
    {
        return toAjax(sysUserChoiceService.copySysUser(sysUserCopy));
    }
    
    @PostMapping("/copytempuser")
    public AjaxResult copyTempUser(@RequestBody SysUserCopy sysUserCopy)
    {
        return toAjax(sysUserChoiceService.copyTempUser(sysUserCopy));
    }
    /**
     * 新增选择用户
     */
    @PostMapping
    public AjaxResult add(@RequestBody SysUserChoice sysUserChoice)
    {
        return toAjax(sysUserChoiceService.insertSysUserChoice(sysUserChoice));
    }

    /**
     * 修改选择用户
     */
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody SysUserChoice sysUserChoice)
    {
        return toAjax(sysUserChoiceService.updateSysUserChoice(sysUserChoice));
    }

    /**
     * 删除选择用户
     */
    @GetMapping("/delete/{choiceCodes}")
    public AjaxResult remove(@PathVariable String[] choiceCodes)
    {
        return toAjax(sysUserChoiceService.deleteSysUserChoiceByIds(choiceCodes));
    }
}
