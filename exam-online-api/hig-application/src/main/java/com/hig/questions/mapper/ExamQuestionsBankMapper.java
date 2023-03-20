package com.hig.questions.mapper;

import java.util.List;
import com.hig.questions.domain.ExamQuestionsBank;

/**
 * 题库管理Mapper接口
 * 
 * @author qnsdt
 * @date 2022-12-19
 */
public interface ExamQuestionsBankMapper 
{
    /**
     * 查询题库管理
     * 
     * @param bankCode 题库管理ID
     * @return 题库管理
     */
    public ExamQuestionsBank selectExamQuestionsBankById(String bankCode);

    /**
     * 查询题库管理列表
     * 
     * @param examQuestionsBank 题库管理
     * @return 题库管理集合
     */
    public List<ExamQuestionsBank> selectExamQuestionsBankList(ExamQuestionsBank examQuestionsBank);

    /**
     * 查询题库管理列表
     * 
     * @param examQuestionsBank 题库管理
     * @return 题库管理集合
     */
    public List<ExamQuestionsBank> selectExamQuestionsBankListByCode(String[] bankCodes);

    /**
     * 新增题库管理
     * 
     * @param examQuestionsBank 题库管理
     * @return 结果
     */
    public int insertExamQuestionsBank(ExamQuestionsBank examQuestionsBank);

    /**
     * 修改题库管理
     * 
     * @param examQuestionsBank 题库管理
     * @return 结果
     */
    public int updateExamQuestionsBank(ExamQuestionsBank examQuestionsBank);

    /**
     * 删除题库管理
     * 
     * @param bankCode 题库管理ID
     * @return 结果
     */
    public int deleteExamQuestionsBankById(String bankCode);

    /**
     * 批量删除题库管理
     * 
     * @param bankCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamQuestionsBankByIds(String[] bankCodes);
}
