package com.hig.questions.mapper;

import java.util.List;
import com.hig.questions.domain.ExamQuestionsAnswer;

/**
 * 考试题目答案Mapper接口
 * 
 * @author qnsdt
 * @date 2022-12-29
 */
public interface ExamQuestionsAnswerMapper 
{
    /**
     * 查询考试题目答案
     * 
     * @param questionsCode 考试题目答案ID
     * @return 考试题目答案
     */
    public ExamQuestionsAnswer selectExamQuestionsAnswerById(String questionsCode);

    /**
     * 查询考试题目答案列表
     * 
     * @param examQuestionsAnswer 考试题目答案
     * @return 考试题目答案集合
     */
    public List<ExamQuestionsAnswer> selectExamQuestionsAnswerList(ExamQuestionsAnswer examQuestionsAnswer);

    /**
     * 查询考试题目答案选项
     * 
     * @param questionsCode 题目代码
     * @return 考试题目答案集合
     */
    public List<ExamQuestionsAnswer> selectExamQuestionsOptionList(String questionsCode);
    
    /**
     * 新增考试题目答案
     * 
     * @param examQuestionsAnswer 考试题目答案
     * @return 结果
     */
    public int insertExamQuestionsAnswer(ExamQuestionsAnswer examQuestionsAnswer);

    /**
     * 修改考试题目答案
     * 
     * @param examQuestionsAnswer 考试题目答案
     * @return 结果
     */
    public int updateExamQuestionsAnswer(ExamQuestionsAnswer examQuestionsAnswer);

    /**
     * 删除考试题目答案
     * 
     * @param questionsCode 考试题目答案ID
     * @return 结果
     */
    public int deleteExamQuestionsAnswerById(String questionsCode);

    /**
     * 批量删除考试题目答案
     * 
     * @param questionsCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamQuestionsAnswerByIds(String[] questionsCodes);
}
