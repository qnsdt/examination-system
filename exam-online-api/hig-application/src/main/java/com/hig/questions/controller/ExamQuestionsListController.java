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
import com.hig.questions.domain.ExamQuestionsList;
import com.hig.questions.domain.ExamQuestionsProperty;
import com.hig.questions.service.IExamQuestionsListService;
import com.hig.questions.service.IExamQuestionsPropertyService;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题目属性Controller
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
@RestController
@RequestMapping("/questions/questionslist")
public class ExamQuestionsListController extends BaseController
{
    @Autowired
    private IExamQuestionsListService examQuestionsListService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询题目属性列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamQuestionsList examQuestionsList)
    {
        startPage();
        List<ExamQuestionsProperty> list = examQuestionsListService.selectExamQuestionsList(examQuestionsList.getBankCodes());
        return getDataTable(list);
    }
    
    @GetMapping("/selectlist/{examCode}")
    public AjaxResult selectList(@PathVariable("examCode") String examCode)
    {
        List<ExamQuestionsProperty> list = examQuestionsListService.selectChoiceExamQuestionsList(examCode);
        return AjaxResult.success(list);
    }


}
