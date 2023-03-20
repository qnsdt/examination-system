package com.hig.fms.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.hig.cms.utils.FileUpload;
import com.hig.fms.common.NotSameFileExpection;
import com.hig.fms.domain.FmsFiles;
import com.hig.fms.domain.MultipartFileParam;
import com.hig.fms.domain.dto.StdOut;
import com.hig.fms.service.ChunkService;
import com.hig.fms.service.IFmsFilesService;
import com.hig.utils.DateUtils;
import com.hig.utils.UUIDGenerator;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件管理Controller
 * 
 * @author qnsdt
 * @date 2021-12-17
 */
@RestController
@RequestMapping("/fms/files")
public class FmsFilesController extends BaseController
{
    @Autowired
    private IFmsFilesService fmsFilesService;

	@Autowired
	ChunkService chunkService;

	@Value("${cms.files.photo-path}")
	private String filespath;

    /**
     * 查询文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('fms:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(FmsFiles fmsFiles)
    {
        startPage();
        List<FmsFiles> list = fmsFilesService.selectFmsFilesList(fmsFiles);
        // System.out.println("文件列表:" + list.toString());
        return getDataTable(list);
    }

    /**
     * 导出文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('fms:files:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FmsFiles fmsFiles)
    {
        List<FmsFiles> list = fmsFilesService.selectFmsFilesList(fmsFiles);
        ExcelUtil<FmsFiles> util = new ExcelUtil<FmsFiles>(FmsFiles.class);
        return util.exportExcel(list, "文件管理数据");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('fms:files:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return AjaxResult.success(fmsFilesService.selectFmsFilesById(fileId));
    }

    /**
     * 新增文件管理
     */
    @PreAuthorize("@ss.hasPermi('fms:files:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FmsFiles fmsFiles)
    {
        return toAjax(fmsFilesService.insertFmsFiles(fmsFiles));
    }

    @RequestMapping("/chunkupload/{guid}/{username}")
    // @PostMapping(value = "/chunkupload")
    public StdOut chunkUpload(@PathVariable String guid,@PathVariable String username,MultipartFileParam param, HttpServletRequest request, HttpServletResponse response) {
        StdOut out = new StdOut();
        
        // 在此更换路径
        // File file = new File("D:\\chunk_test");//存储路径
    	// 拼接路径
        String fileurl = filespath + "/" + DateUtils.toDateString() + "/" + guid ;
        // System.out.println("拼接地址为:" + fileurl);
    	String filepath = RuoYiConfig.getProfile() + fileurl;
    	// System.out.println("拼接路径为:" + filepath);
    	File file = new File(filepath);//存储路径

 
        String path = file.getAbsolutePath();
        response.setContentType("text/html;charset=UTF-8");
 
        try {
            //判断前端Form表单格式是否支持文件上传
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                out.setCode(StdOut.PARAMETER_NULL);
                out.setMessage("表单格式错误");
                return out;
            } else {
                param.setTaskId(param.getIdentifier());
                out.setModel(chunkService.chunkUploadByMappedByteBuffer(param, path));
                // System.out.println("保存后:" + out.toString());
                if (out.getModel() != null) {
                	// System.out.println("文件名:" + out.getModel().toString());
                	// 保存文件信息
                	// 取得文件名
                	String filename = out.getModel().toString();
                	// 取文件类型
                	String suffix = getSuffix(filename);
                	
                	// 开始保存
                	// String fileTitle, String fileDescribe, String fileName, String filePath, String fileUrl,
        			// String fileSuffix, String originalName, String uploadName
                	FmsFiles fmsFiles = new FmsFiles(null, null, filename, filepath, fileurl, suffix, filename, username);
                	System.out.println(fmsFiles.toString());
                    int count = fmsFilesService.insertFmsFiles(fmsFiles);
                    
                }
                return out;
            }
        } catch (NotSameFileExpection e) {
            out.setCode(StdOut.FAIL);
            out.setMessage("MD5校验失败");
            return out;
        } catch (Exception e) {
            out.setCode(StdOut.FAIL);
            out.setMessage("上传失败");
            return out;
        }
    }
    
    private String getSuffix(String filename) {
    	String suffix = null;
    	try
    	{
    		suffix = filename.substring(filename.lastIndexOf(".") + 1);
    	}
    	catch(Exception e) {
    		
    	}
    	
    	return suffix;
    }
    /**
     * 修改文件管理
     */
    @PreAuthorize("@ss.hasPermi('fms:files:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FmsFiles fmsFiles)
    {
    	System.out.println("fmsFiles:" + fmsFiles.toString());
        return toAjax(fmsFilesService.updateFmsFiles(fmsFiles));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize("@ss.hasPermi('fms:files:remove')")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(fmsFilesService.deleteFmsFilesByIds(fileIds));
    }
}
