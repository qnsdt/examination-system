package com.hig.questions.controller;

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
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.hig.cms.utils.FileUpload;
import com.hig.questions.domain.ExamBankPicture;
import com.hig.questions.domain.ExamQuestionsBank;
import com.hig.questions.service.IExamBankPictureService;
import com.hig.questions.service.IExamQuestionsBankService;
import com.hig.utils.UUIDGenerator;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题库管理Controller
 * 
 * @author qnsdt
 * @date 2022-12-19
 */
@RestController
@RequestMapping("/questions/questionsbank")
public class ExamQuestionsBankController extends BaseController
{
	@Value("${cms.exam.photo-path}")
	private String photopath;

    @Autowired
    private IExamQuestionsBankService examQuestionsBankService;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private IExamBankPictureService examBankPictureService;

    /**
     * 查询题库管理列表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsbank:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestionsBank examQuestionsBank)
    {
        startPage();
        List<ExamQuestionsBank> list = examQuestionsBankService.selectExamQuestionsBankList(examQuestionsBank);
        return getDataTable(list);
    }

    @GetMapping("/arraylist/{bankCodes}")
    public AjaxResult arrayList(@PathVariable("bankCodes") String bankCodes)
    {
        // startPage();
    	// System.out.println("bankCodes:" + bankCodes);
        List<ExamQuestionsBank> list = examQuestionsBankService.selectExamQuestionsBankListByCode(bankCodes.split(","));
        return AjaxResult.success(list);
    }
    
    /**
     * 导出题库管理列表
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsbank:export')")
    @Log(title = "题库管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestionsBank examQuestionsBank)
    {
        List<ExamQuestionsBank> list = examQuestionsBankService.selectExamQuestionsBankList(examQuestionsBank);
        ExcelUtil<ExamQuestionsBank> util = new ExcelUtil<ExamQuestionsBank>(ExamQuestionsBank.class);
        return util.exportExcel(list, "题库管理数据");
    }

    /**
     * 获取题库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsbank:query')")
    @GetMapping(value = "/{bankCode}")
    public AjaxResult getInfo(@PathVariable("bankCode") String bankCode)
    {
        return AjaxResult.success(examQuestionsBankService.selectExamQuestionsBankById(bankCode));
    }

    /**
     * 新增题库管理
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsbank:add')")
    @Log(title = "题库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamQuestionsBank examQuestionsBank)
    {
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    	System.out.println(loginUser.getUser().getUserName());
    	System.out.println(loginUser.getUser().getDept().getDeptId());
    	examQuestionsBank.setCreateBy(loginUser.getUser().getUserName());
    	examQuestionsBank.setCreateDept(loginUser.getUser().getDept().getDeptId());
    	examQuestionsBank.setBankCode(UUIDGenerator.generate());
    	
    	System.out.println("examQuestionsBank:" + examQuestionsBank.toString());
        return toAjax(examQuestionsBankService.insertExamQuestionsBank(examQuestionsBank));
    }

    /**
     * 修改题库管理
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsbank:edit')")
    @Log(title = "题库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamQuestionsBank examQuestionsBank)
    {
    	System.out.println("examQuestionsBank:" + examQuestionsBank.toString());
        return toAjax(examQuestionsBankService.updateExamQuestionsBank(examQuestionsBank));
    }

    /**
     * 删除题库管理
     */
    @PreAuthorize("@ss.hasPermi('questions:questionsbank:remove')")
    @Log(title = "题库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bankCodes}")
    public AjaxResult remove(@PathVariable String[] bankCodes)
    {
        return toAjax(examQuestionsBankService.deleteExamQuestionsBankByIds(bankCodes));
    }
    
    /**
     * 新增参数配置
     */
    @PostMapping("/uploadphoto/{bankcode}")
    public AjaxResult uploadPhoto(@PathVariable String bankcode,
    		@RequestParam("file") MultipartFile  file)
    {
    	// 取得原始文件名
    	String originalfile = file.getOriginalFilename();
    	
    	// 拼接路径
    	String path = RuoYiConfig.getProfile() + photopath;
    	System.out.println("拼接路径为:" + path);
        String filename = FileUpload.writeUploadFile(file,path);
        String fileurl = photopath + "/" + filename;
        System.out.println(fileurl);
        

    	int count = 0;
    	
    	// 相应赋值
		ExamBankPicture examBankPicture = new ExamBankPicture(bankcode, path, fileurl, filename, originalfile);
		
		System.out.println("examBankPicture:" + examBankPicture.toString());
		
		try
		{
			examBankPictureService.deleteExamBankPictureById(bankcode);
			count = examBankPictureService.insertExamBankPicture(examBankPicture);
		}
		catch(Exception e)
		{
			return AjaxResult.error(e.getMessage());
		}
		
        return AjaxResult.success(examBankPicture);
    }
}
