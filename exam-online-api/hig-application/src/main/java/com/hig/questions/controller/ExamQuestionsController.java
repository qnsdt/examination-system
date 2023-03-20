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
import com.hig.questions.domain.ExamQuestions;
import com.hig.questions.domain.ExamQuestionsAnswer;
import com.hig.questions.domain.ExamQuestionsContent;
import com.hig.questions.domain.ExamQuestionsProperty;
import com.hig.questions.service.IExamQuestionsAnswerService;
import com.hig.questions.service.IExamQuestionsContentService;
import com.hig.questions.service.IExamQuestionsPropertyService;
import com.hig.questions.service.IExamQuestionsService;
import com.hig.utils.UUIDGenerator;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考试题目Controller
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
@RestController
@RequestMapping("/questions/examquestions")
public class ExamQuestionsController extends BaseController
{
    @Autowired
    private IExamQuestionsService examQuestionsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IExamQuestionsAnswerService examQuestionsAnswerService;

    @Autowired
    private IExamQuestionsContentService examQuestionsContentService;

    @Autowired
    private IExamQuestionsPropertyService examQuestionsPropertyService;

    /**
     * 查询考试题目列表
     */
    @PreAuthorize("@ss.hasPermi('questions:examquestions:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestions examQuestions)
    {
        startPage();
        List<ExamQuestions> list = examQuestionsService.selectExamQuestionsList(examQuestions);
        return getDataTable(list);
    }

    /**
     * 导出考试题目列表
     */
    @PreAuthorize("@ss.hasPermi('questions:examquestions:export')")
    @Log(title = "考试题目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ExamQuestions examQuestions)
    {
        List<ExamQuestions> list = examQuestionsService.selectExamQuestionsList(examQuestions);
        ExcelUtil<ExamQuestions> util = new ExcelUtil<ExamQuestions>(ExamQuestions.class);
        return util.exportExcel(list, "考试题目数据");
    }

    /**
     * 获取考试题目详细信息
     */
    @PreAuthorize("@ss.hasPermi('questions:examquestions:query')")
    @GetMapping(value = "/{questionsCode}")
    public AjaxResult getInfo(@PathVariable("questionsCode") String questionsCode)
    {
        return AjaxResult.success(examQuestionsService.selectExamQuestionsById(questionsCode));
    }

    /**
     * 新增考试题目
     */
    @PreAuthorize("@ss.hasPermi('questions:examquestions:add')")
    @Log(title = "考试题目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ExamQuestions examQuestions)
    {
    	// 取得用户单位
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
    	examQuestions.setCreateBy(loginUser.getUser().getUserName());
    	examQuestions.setCreateDept(loginUser.getUser().getDept().getDeptId());
        List<ExamQuestionsAnswer> answerList = examQuestions.getAnswerList();
        
        ExamQuestionsProperty examQuestionsProperty = new ExamQuestionsProperty(examQuestions.getBankCode(),null,examQuestions.getQuestionsCode(),examQuestions.getQuestionsTitle(),
        		examQuestions.getQuestionsType(),examQuestions.getQuestionsScore(),examQuestions.getRateNumber(),examQuestions.getRightAnswer(),examQuestions.getAnswerAnalyse(),
        		0,examQuestions.getCreateBy(), examQuestions.getCreateDept());
        
        ExamQuestionsContent examQuestionsContent =  new ExamQuestionsContent(examQuestions.getQuestionsCode(), examQuestions.getQuestionsContent());
        
        System.out.println("examQuestionsContent:" + examQuestionsContent.toString());
        
        // 开始保存数据
        
        // 保存题目选项
        // 安全删除
        examQuestionsAnswerService.deleteExamQuestionsAnswerById(examQuestions.getQuestionsCode());
        for (ExamQuestionsAnswer questionsAnswer: answerList) {
        	questionsAnswer.setOrderId(null);
            examQuestionsAnswerService.insertExamQuestionsAnswer(questionsAnswer);
        }
        
        // 保存题目数据
        int count = 0;
        examQuestionsContentService.deleteExamQuestionsContentById(examQuestions.getQuestionsCode());
        
        count = examQuestionsContentService.insertExamQuestionsContent(examQuestionsContent);
        
        if (count == 0) {
        	return new AjaxResult(0,"更新题目信息出错!");
        }
        
        
        // 安全删除
        examQuestionsPropertyService.deleteExamQuestionsPropertyById(examQuestions.getQuestionsCode());
        
        return toAjax(examQuestionsPropertyService.insertExamQuestionsProperty(examQuestionsProperty));
    }

    /**
     * 修改考试题目
     */
    @PreAuthorize("@ss.hasPermi('questions:examquestions:edit')")
    @Log(title = "考试题目", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody ExamQuestions examQuestions)
    {
        List<ExamQuestionsAnswer> answerList = examQuestions.getAnswerList();
        
        ExamQuestionsProperty examQuestionsProperty = new ExamQuestionsProperty(examQuestions.getBankCode(),null,examQuestions.getQuestionsCode(),examQuestions.getQuestionsTitle(),
        		examQuestions.getQuestionsType(),examQuestions.getQuestionsScore(),examQuestions.getRateNumber(),examQuestions.getRightAnswer(),examQuestions.getAnswerAnalyse(),
        		0,examQuestions.getCreateBy(), examQuestions.getCreateDept());
        
        ExamQuestionsContent examQuestionsContent =  new ExamQuestionsContent(examQuestions.getQuestionsCode(), examQuestions.getQuestionsContent());
        
        
        // 开始保存数据
        
        // 保存题目选项
        // 安全删除
        examQuestionsAnswerService.deleteExamQuestionsAnswerById(examQuestions.getQuestionsCode());
        for (ExamQuestionsAnswer questionsAnswer: answerList) {
            examQuestionsAnswerService.insertExamQuestionsAnswer(questionsAnswer);
        }
        
        // 保存题目数据
        int count = 0;
        examQuestionsContentService.deleteExamQuestionsContentById(examQuestions.getQuestionsCode());
        
        count = examQuestionsContentService.insertExamQuestionsContent(examQuestionsContent);
        
        if (count == 0) {
        	return new AjaxResult(0,"更新题目信息出错!");
        }
        
        
        return toAjax(examQuestionsPropertyService.updateExamQuestionsProperty(examQuestionsProperty));

    }

    /**
     * 删除考试题目
     */
    @PreAuthorize("@ss.hasPermi('questions:examquestions:remove')")
    @Log(title = "考试题目", businessType = BusinessType.DELETE)
    @GetMapping("/delete/{questionsCodes}")
    public AjaxResult remove(@PathVariable String[] questionsCodes)
    {
    	int count = questionsCodes.length;
    	for (String questionsCode: questionsCodes) {
            examQuestionsAnswerService.deleteExamQuestionsAnswerById(questionsCode);
            examQuestionsContentService.deleteExamQuestionsContentById(questionsCode);
            examQuestionsPropertyService.deleteExamQuestionsPropertyById(questionsCode);
    	}
    	
    	
        return toAjax(count);
    }
}
