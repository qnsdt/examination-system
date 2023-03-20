package com.hig.onlineexam.mapper;

import java.util.List;
import com.hig.onlineexam.domain.ExamFinishAnswer;

/**
 * 考试成绩Mapper接口
 * 
 * @author qnsdt
 * @date 2023-02-24
 */
public interface ExamFinishAnswerMapper 
{
    /**
     * 查询考试成绩
     * 
     * @param examCode 考试成绩ID
     * @return 考试成绩
     */
    public ExamFinishAnswer selectExamFinishAnswerById(String examCode);

    /**
     * 查询考试成绩列表
     * 
     * @param examFinishAnswer 考试成绩
     * @return 考试成绩集合
     */
    public List<ExamFinishAnswer> selectExamFinishAnswerList(ExamFinishAnswer examFinishAnswer);

    /**
     * 新增考试成绩
     * 
     * @param examFinishAnswer 考试成绩
     * @return 结果
     */
    public int insertExamFinishAnswer(ExamFinishAnswer examFinishAnswer);

    /**
     * 考试结束将考试成绩拷入
     * 
     * @param examFinishAnswer 考试成绩
     * @return 结果
     */
    public int intoExamFinishAnswer(ExamFinishAnswer examFinishAnswer);

    /**
     * 修改考试成绩
     * 
     * @param examFinishAnswer 考试成绩
     * @return 结果
     */
    public int updateExamFinishAnswer(ExamFinishAnswer examFinishAnswer);

    /**
     * 删除考试成绩
     * 
     * @param examCode 考试成绩ID
     * @return 结果
     */
    public int deleteExamFinishAnswerById(String examCode);

    /**
     * 删除个人考试成绩
     * 
     * @param examFinishAnswer 考试成绩
     * @return 结果
     */
    public int deleteExamFinishAnswer(ExamFinishAnswer examFinishAnswer);

    /**
     * 批量删除考试成绩
     * 
     * @param examCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamFinishAnswerByIds(String[] examCodes);
}
