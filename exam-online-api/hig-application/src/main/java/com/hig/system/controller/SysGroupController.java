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
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.hig.system.domain.SysGroup;
import com.hig.system.service.ISysGroupService;
import com.hig.utils.UUIDGenerator;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人员分组Controller
 * 
 * @author qnsdt
 * @date 2023-01-28
 */
@RestController
@RequestMapping("/system/group")
public class SysGroupController extends BaseController
{
    @Autowired
    private ISysGroupService sysGroupService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询人员分组列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysGroup sysGroup)
    {
        startPage();
        List<SysGroup> list = sysGroupService.selectSysGroupList(sysGroup);
        return getDataTable(list);
    }

    @GetMapping("/choicelist/{examCode}")
    public TableDataInfo selectChoiceSysGroupList(@PathVariable String examCode) {
		// TODO Auto-generated method stub
    	startPage();
        List<SysGroup> list = sysGroupService.selectChoiceSysGroupList(examCode);
        return getDataTable(list);
	}
    /**
     * 导出人员分组列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:export')")
    @Log(title = "人员分组", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysGroup sysGroup)
    {
        List<SysGroup> list = sysGroupService.selectSysGroupList(sysGroup);
        ExcelUtil<SysGroup> util = new ExcelUtil<SysGroup>(SysGroup.class);
        return util.exportExcel(list, "人员分组数据");
    }

    /**
     * 获取人员分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return AjaxResult.success(sysGroupService.selectSysGroupById(groupId));
    }

    /**
     * 新增人员分组
     */
    @PreAuthorize("@ss.hasPermi('system:group:add')")
    @Log(title = "人员分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysGroup sysGroup)
    {
    	sysGroup.setGroupCode(UUIDGenerator.generate());
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    	sysGroup.setCreateBy(loginUser.getUser().getUserName());

        return toAjax(sysGroupService.insertSysGroup(sysGroup));
    }

    /**
     * 修改人员分组
     */
    @PreAuthorize("@ss.hasPermi('system:group:edit')")
    @Log(title = "人员分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysGroup sysGroup)
    {
        return toAjax(sysGroupService.updateSysGroup(sysGroup));
    }

    /**
     * 删除人员分组
     */
    @PreAuthorize("@ss.hasPermi('system:group:remove')")
    @Log(title = "人员分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(sysGroupService.deleteSysGroupByIds(groupIds));
    }
}
