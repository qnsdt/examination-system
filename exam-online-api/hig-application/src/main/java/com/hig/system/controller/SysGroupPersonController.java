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
import com.hig.system.domain.SysGroupPerson;
import com.hig.system.service.ISysGroupPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分组用户Controller
 * 
 * @author qnsdt
 * @date 2023-02-06
 */
@RestController
@RequestMapping("/system/groupperson")
public class SysGroupPersonController extends BaseController
{
    @Autowired
    private ISysGroupPersonService sysGroupPersonService;

    /**
     * 查询分组用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysGroupPerson sysGroupPerson)
    {
        startPage();
        List<SysGroupPerson> list = sysGroupPersonService.selectSysGroupPersonList(sysGroupPerson);
        return getDataTable(list);
    }

    /**
     * 导出分组用户列表
     */
    @GetMapping("/export")
    public AjaxResult export(SysGroupPerson sysGroupPerson)
    {
        List<SysGroupPerson> list = sysGroupPersonService.selectSysGroupPersonList(sysGroupPerson);
        ExcelUtil<SysGroupPerson> util = new ExcelUtil<SysGroupPerson>(SysGroupPerson.class);
        return util.exportExcel(list, "分组用户数据");
    }

    /**
     * 获取分组用户详细信息
     */
    @GetMapping(value = "/{groupCode}")
    public AjaxResult getInfo(@PathVariable("groupCode") String groupCode)
    {
        return AjaxResult.success(sysGroupPersonService.selectSysGroupPersonById(groupCode));
    }

    /**
     * 新增分组用户
     */
    @PostMapping
    public AjaxResult add(@RequestBody List<SysGroupPerson> sysGroupPersonList)
    {
        return toAjax(sysGroupPersonService.insertSysGroupPerson(sysGroupPersonList));
    }

    /**
     * 修改分组用户
     */
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody SysGroupPerson sysGroupPerson)
    {
        return toAjax(sysGroupPersonService.updateSysGroupPerson(sysGroupPerson));
    }

    /**
     * 删除分组用户
     */
    @GetMapping("/delete/{groupCodes}")
    public AjaxResult remove(@PathVariable String[] groupCodes)
    {
        return toAjax(sysGroupPersonService.deleteSysGroupPersonByIds(groupCodes));
    }
}
