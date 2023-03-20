package com.hig.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hig.system.domain.TempUserChoice;
import com.hig.system.domain.dto.SysUserData;
import com.hig.system.mapper.SysUserDataMapper;
import com.hig.system.service.ISysUserDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;

@RestController
@RequestMapping("/system/userdata")
public class SysUserDataController extends BaseController{
	@Autowired
	ISysUserDataService sysUserDataService;

    /**
     * 查询用户选择列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysUserData sysUser)
    {
        startPage();
        List<SysUserData> list = sysUserDataService.selectUserList(sysUser);
        return getDataTable(list);
    }
    
    @GetMapping("/choicelist")
    public TableDataInfo choiceList(SysUserData sysUser)
    {
        startPage();
        List<SysUserData> list = sysUserDataService.selectChoiceUserList(sysUser);
        return getDataTable(list);
    }
}
