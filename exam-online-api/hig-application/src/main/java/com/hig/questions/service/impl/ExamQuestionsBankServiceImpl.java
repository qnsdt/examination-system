package com.hig.questions.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.questions.mapper.ExamQuestionsBankMapper;
import com.hig.questions.domain.ExamQuestionsBank;
import com.hig.questions.service.IExamQuestionsBankService;

/**
 * 题库管理Service业务层处理
 * 
 * @author qnsdt
 * @date 2022-12-19
 */
@Service
public class ExamQuestionsBankServiceImpl implements IExamQuestionsBankService 
{
    @Autowired
    private ExamQuestionsBankMapper examQuestionsBankMapper;

    /**
     * 查询题库管理
     * 
     * @param bankCode 题库管理ID
     * @return 题库管理
     */
    @Override
    public ExamQuestionsBank selectExamQuestionsBankById(String bankCode)
    {
        return examQuestionsBankMapper.selectExamQuestionsBankById(bankCode);
    }

    /**
     * 查询题库管理列表
     * 
     * @param examQuestionsBank 题库管理
     * @return 题库管理
     */
    @Override
    public List<ExamQuestionsBank> selectExamQuestionsBankList(ExamQuestionsBank examQuestionsBank)
    {
        return examQuestionsBankMapper.selectExamQuestionsBankList(examQuestionsBank);
    }

    /**
     * 新增题库管理
     * 
     * @param examQuestionsBank 题库管理
     * @return 结果
     */
    @Override
    public int insertExamQuestionsBank(ExamQuestionsBank examQuestionsBank)
    {
        examQuestionsBank.setCreateTime(DateUtils.getNowDate());
        return examQuestionsBankMapper.insertExamQuestionsBank(examQuestionsBank);
    }

    /**
     * 修改题库管理
     * 
     * @param examQuestionsBank 题库管理
     * @return 结果
     */
    @Override
    public int updateExamQuestionsBank(ExamQuestionsBank examQuestionsBank)
    {
        return examQuestionsBankMapper.updateExamQuestionsBank(examQuestionsBank);
    }

    /**
     * 批量删除题库管理
     * 
     * @param bankCodes 需要删除的题库管理ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsBankByIds(String[] bankCodes)
    {
        return examQuestionsBankMapper.deleteExamQuestionsBankByIds(bankCodes);
    }

    /**
     * 删除题库管理信息
     * 
     * @param bankCode 题库管理ID
     * @return 结果
     */
    @Override
    public int deleteExamQuestionsBankById(String bankCode)
    {
        return examQuestionsBankMapper.deleteExamQuestionsBankById(bankCode);
    }

	@Override
	public List<ExamQuestionsBank> selectExamQuestionsBankListByCode(String[] bankCodes) {
		// TODO Auto-generated method stub
		return examQuestionsBankMapper.selectExamQuestionsBankListByCode(bankCodes);
	}


}
