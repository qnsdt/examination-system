package com.hig.questions.service;

import java.util.List;
import com.hig.questions.domain.ExamQuestionsProperty;

/**
 * 题目属性Service接口
 * 
 * @author qnsdt
 * @date 2022-12-28
 */
public interface IExamQuestionsListService 
{

    /**
     * 查询题目属性列表
     * 
     * @param examQuestionsProperty 题目属性
     * @return 题目属性集合
     */
    public List<ExamQuestionsProperty> selectExamQuestionsList(String[] bankCodes);
    
    public List<ExamQuestionsProperty> selectChoiceExamQuestionsList(String examCode);


}
