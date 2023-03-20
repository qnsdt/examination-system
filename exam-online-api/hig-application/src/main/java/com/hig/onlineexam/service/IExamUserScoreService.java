package com.hig.onlineexam.service;

import java.util.List;
import com.hig.onlineexam.domain.ExamUserScore;

/**
 * 考试成绩Service接口
 * 
 * @author qnsdt
 * @date 2023-03-03
 */
public interface IExamUserScoreService 
{
    /**
     * 查询考试成绩
     * 
     * @param examCode 考试成绩ID
     * @return 考试成绩
     */
    public ExamUserScore selectExamUserScoreById(String examCode);

    /**
     * 查询考试成绩列表
     * 
     * @param examUserScore 考试成绩
     * @return 考试成绩集合
     */
    public List<ExamUserScore> selectExamUserScoreList(ExamUserScore examUserScore);

    /**
     * 新增考试成绩
     * 
     * @param examUserScore 考试成绩
     * @return 结果
     */
    public int insertExamUserScore(ExamUserScore examUserScore);

    /**
     * 维护考试成绩
     * 
     * @param examUserScore 考试成绩
     * @return 结果
     */
     public int intoExamUserScore(ExamUserScore examUserScore);

    /**
     * 修改考试成绩
     * 
     * @param examUserScore 考试成绩
     * @return 结果
     */
    public int updateExamUserScore(ExamUserScore examUserScore);

    /**
     * 批量删除考试成绩
     * 
     * @param examCodes 需要删除的考试成绩ID
     * @return 结果
     */
    
    /**
     * 删除考试成绩
     * 
     * @param examCode 考试成绩ID
     * @return 结果
     */
    public int deleteExamUserScore(ExamUserScore examUserScore);

    public int deleteExamUserScoreByIds(String[] examCodes);

    /**
     * 删除考试成绩信息
     * 
     * @param examCode 考试成绩ID
     * @return 结果
     */
    public int deleteExamUserScoreById(String examCode);
}
