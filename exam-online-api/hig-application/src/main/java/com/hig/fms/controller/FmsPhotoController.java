package com.hig.fms.controller;

import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.hig.cms.utils.FileUpload;
import com.hig.fms.domain.FmsPhoto;
import com.hig.fms.service.IFmsPhotoService;
import com.hig.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图片管理Controller
 * 
 * @author qnsdt
 * @date 2021-12-14
 */
@RestController
@RequestMapping("/fms/photo")
public class FmsPhotoController extends BaseController
{
    @Autowired
    private IFmsPhotoService fmsPhotoService;

	@Value("${cms.fms.photo-path}")
	private String fmspath;

    private TokenService tokenService;

    /**
     * 查询图片管理列表
     */
    @PreAuthorize("@ss.hasPermi('fms:photo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmsPhoto fmsPhoto)
    {
        startPage();
        List<FmsPhoto> list = fmsPhotoService.selectFmsPhotoList(fmsPhoto);
        return getDataTable(list);
    }

    /**
     * 导出图片管理列表
     */
    @PreAuthorize("@ss.hasPermi('fms:photo:export')")
    @Log(title = "图片管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FmsPhoto fmsPhoto)
    {
        List<FmsPhoto> list = fmsPhotoService.selectFmsPhotoList(fmsPhoto);
        ExcelUtil<FmsPhoto> util = new ExcelUtil<FmsPhoto>(FmsPhoto.class);
        return util.exportExcel(list, "图片管理数据");
    }

    /**
     * 获取图片管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('fms:photo:query')")
    @GetMapping(value = "/{photoId}")
    public AjaxResult getInfo(@PathVariable("photoId") Long photoId)
    {
        return AjaxResult.success(fmsPhotoService.selectFmsPhotoById(photoId));
    }

    /**
     * 新增图片管理
     */
    // @PreAuthorize("@ss.hasPermi('fms:photo:add')")
    // @Log(title = "图片管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add/{username}")
    public AjaxResult add(@PathVariable String username,@RequestParam("file") MultipartFile  file)
    {
    	if (username == null || username.trim().equals("")) {
    		return AjaxResult.error("用户名不能为空");
    	}
    	System.out.println("当前日期:" + DateUtils.toDateString());
    	// 取得原始文件名
    	String originalfile = file.getOriginalFilename();
    	
    	// 拼接路径
    	String path = RuoYiConfig.getProfile() + fmspath + "/" + DateUtils.toDateString();
    	System.out.println("拼接路径为:" + path);
        String filename = FileUpload.writeUploadFile(file,path);
        String fileurl = fmspath + "/" + DateUtils.toDateString() + "/" + filename;
        System.out.println(fileurl);
        

    	int count = 0;
    	
		// System.out.println("photoid:" + swipperid);
		
		System.out.println("RuoYiConfig.getProfile()" + RuoYiConfig.getProfile());
    	
		/*LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		
		System.out.println("取得用户信息:" + user.toString());*/
        
        // String fileName, String photoPath, String photoUrl, String originalName, String uploadName,String uploadDept, Long status
        FmsPhoto fmsPhoto = new FmsPhoto(filename, path, fileurl, originalfile, username, "", (long) 0);
        
        // System.out.println("fmsPhoto:" + fmsPhoto.toString());
        try
        {
        	count = fmsPhotoService.insertFmsPhoto(fmsPhoto);
        }
        catch(Exception e)
        {
        	return AjaxResult.error(e.getMessage());
        }
        // select photo_id,file_name,photo_path,photo_url,original_name,upload_name,upload_dept,upload_time,status from fms_photo;
        
    	// 相应赋值
    	//int photoId, int swipperId, String photoPath, String photoUrl, String fileName,String originalName
		
		/*SysSwipper sysSwipper = new SysSwipper(Integer.parseInt(swipperid), path, fileurl, filename, originalfile);
		
		System.out.println("sysSwipper:" + sysSwipper.toString());
		try
		{
			count = swipperService.insertSwipper(sysSwipper);
		}
		catch(Exception e)
		{
			return AjaxResult.error(e.getMessage());
		}*/
		
		// FmsPhoto fmsPhoto
       
        // return AjaxResult.success(sysSwipper);

        return toAjax(fmsPhotoService.insertFmsPhoto(fmsPhoto));
    }

    /**
     * 修改图片管理
     */
    @PreAuthorize("@ss.hasPermi('fms:photo:edit')")
    @Log(title = "图片管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmsPhoto fmsPhoto)
    {
        return toAjax(fmsPhotoService.updateFmsPhoto(fmsPhoto));
    }

    /**
     * 删除图片管理
     */
    @PreAuthorize("@ss.hasPermi('fms:photo:remove')")
    @Log(title = "图片管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{photoIds}")
    public AjaxResult remove(@PathVariable Long[] photoIds)
    {
        return toAjax(fmsPhotoService.deleteFmsPhotoByIds(photoIds));
    }
}
