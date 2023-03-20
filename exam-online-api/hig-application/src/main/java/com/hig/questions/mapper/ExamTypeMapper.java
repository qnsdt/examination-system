package com.hig.questions.mapper;

import java.util.List;
import com.hig.questions.domain.ExamType;

/**
 * 考试分类Mapper接口
 * 
 * @author qnsdt
 * @date 2022-12-14
 */
public interface ExamTypeMapper 
{
    /**
     * 查询考试分类
     * 
     * @param typeId 考试分类ID
     * @return 考试分类
     */
    public ExamType selectExamTypeById(Long typeId);

    /**
     * 查询考试分类列表
     * 
     * @param examType 考试分类
     * @return 考试分类集合
     */
    public List<ExamType> selectExamTypeList(ExamType examType);

    /**
     * 新增考试分类
     * 
     * @param examType 考试分类
     * @return 结果
     */
    public int insertExamType(ExamType examType);

    /**
     * 修改考试分类
     * 
     * @param examType 考试分类
     * @return 结果
     */
    public int updateExamType(ExamType examType);

    /**
     * 删除考试分类
     * 
     * @param typeId 考试分类ID
     * @return 结果
     */
    public int deleteExamTypeById(Long typeId);

    /**
     * 批量删除考试分类
     * 
     * @param typeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamTypeByIds(Long[] typeIds);
}
