package com.hig.questions.service;

import java.util.List;
import com.hig.questions.domain.ExamType;
import com.hig.questions.domain.TypeTreeSelect;
import com.ruoyi.common.core.domain.entity.SysDept;

/**
 * 考试分类Service接口
 * 
 * @author qnsdt
 * @date 2022-12-14
 */
public interface IExamTypeService 
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
     * 构建前端所需要树结构
     * 
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<ExamType> buildTypeTree(List<ExamType> examTypeList);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TypeTreeSelect> buildTypeTreeSelect(List<ExamType> examTypeList);

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
     * 批量删除考试分类
     * 
     * @param typeIds 需要删除的考试分类ID
     * @return 结果
     */
    public int deleteExamTypeByIds(Long[] typeIds);

    /**
     * 删除考试分类信息
     * 
     * @param typeId 考试分类ID
     * @return 结果
     */
    public int deleteExamTypeById(Long typeId);
}
