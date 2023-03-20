package com.hig.exam.controller;

import java.util.List;
import java.util.Map;

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
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.hig.cms.utils.FileUpload;
import com.hig.exam.domain.ExamTaskManager;
import com.hig.exam.domain.ExamTaskPicture;
import com.hig.exam.service.IExamTaskManagerService;
import com.hig.exam.service.IExamTaskPictureService;
import com.hig.questions.domain.ExamBankPicture;
import com.hig.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 创建考试Controller
 * 
 * @author qnsdt
 * @date 2023-01-06
 */
@RestController
@RequestMapping("/exam/examtask")
public class ExamTaskManagerController extends BaseController
{
	@Value("${cms.exam.photo-path}")
	private String photopath;

    @Autowired
    private IExamTaskManagerService examTaskManagerService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IExamTaskPictureService examTaskPictureService;
    /**
     * 查询创建考试列表
     */
    @PreAuthorize("@ss.hasPermi('exam:examtask:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamTaskManager examTaskManager)
    {
    	
    	if (examTaskManager.getStartTime() != null) {
    		examTaskManager.setStartDateText(DateUtils.toShortDateString(examTaskManager.getStartTime()));
    	}
    	
    	if (examTaskManager.getEndTime() != null) {
    		examTaskManager.setEndDateText(DateUtils.toShortDateString(examTaskManager.getEndTime()));
    	}

        startPage();
        List<ExamTaskManager> list = examTaskManagerService.selectExamTaskManagerList(examTaskManager);
        return getDataTable(list);
    }

    /**
     * 导出创建考试列表
     */
    @PreAuthorize("@ss.hasPermi('exam:examtask:export')")
    @Log(title = "创建考试", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamTaskManager examTaskManager)
    {
        List<ExamTaskManager> list = examTaskManagerService.selectExamTaskManagerList(examTaskManager);
        ExcelUtil<ExamTaskManager> util = new ExcelUtil<ExamTaskManager>(ExamTaskManager.class);
        return util.exportExcel(list, "创建考试数据");
    }

    /**
     * 获取创建考试详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:examtask:query')")
    @GetMapping(value = "/{examCode}")
    public AjaxResult getInfo(@PathVariable("examCode") String examCode)
    {
        return AjaxResult.success(examTaskManagerService.selectExamTaskManagerById(examCode));
    }

    /**
     * 新增创建考试
     */
    @PreAuthorize("@ss.hasPermi('exam:examtask:add')")
    @Log(title = "创建考试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ExamTaskManager examTaskManager)
    {
        return toAjax(examTaskManagerService.insertExamTaskManager(examTaskManager));
    }

    /**
     * 修改创建考试
     */
    @PreAuthorize("@ss.hasPermi('exam:examtask:edit')")
    @Log(title = "创建考试", businessType = BusinessType.UPDATE)
    // @PutMapping
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamTaskManager examTaskManager)
    {
        return toAjax(examTaskManagerService.updateExamTaskManager(examTaskManager));
    }

    /**
     * 删除创建考试
     */
    @PreAuthorize("@ss.hasPermi('exam:examtask:remove')")
    @Log(title = "创建考试", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{examCodes}")
    public AjaxResult remove(@PathVariable String[] examCodes)
    {
        return toAjax(examTaskManagerService.deleteExamTaskManagerByIds(examCodes));
    }
    
    /**
     * 新增参数配置
     */
    @PostMapping("/uploadphoto/{examCodes}")
    public AjaxResult uploadPhoto(@PathVariable String examCodes,
    		@RequestParam("file") MultipartFile  file)
    {
    	// 取得原始文件名
    	String originalfile = file.getOriginalFilename();
    	
    	// 拼接路径
    	String path = RuoYiConfig.getProfile() + photopath;
        String filename = FileUpload.writeUploadFile(file,path);
        String fileurl = photopath + "/" + filename;

    	int count = 0;
    	
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

    	ExamTaskPicture examTaskPicture = new ExamTaskPicture(examCodes,path,fileurl,filename,originalfile,loginUser.getUser().getUserName());
        System.out.println("examTaskPicture:" + examTaskPicture.toString());
		
		try
		{
			examTaskPictureService.deleteExamTaskPictureById(examCodes);
			count = examTaskPictureService.insertExamTaskPicture(examTaskPicture);
		}
		catch(Exception e)
		{
			return AjaxResult.error(e.getMessage());
		}
    	
		
        return AjaxResult.success(examTaskPicture);
    }
}
