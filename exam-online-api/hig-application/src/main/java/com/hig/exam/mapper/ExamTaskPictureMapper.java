package com.hig.exam.mapper;

import java.util.List;
import com.hig.exam.domain.ExamTaskPicture;

/**
 * 考试任务图片管理Mapper接口
 * 
 * @author qnsdt
 * @date 2023-01-18
 */
public interface ExamTaskPictureMapper 
{
    /**
     * 查询考试任务图片管理
     * 
     * @param photoCode 考试任务图片管理ID
     * @return 考试任务图片管理
     */
    public ExamTaskPicture selectExamTaskPictureById(String photoCode);

    /**
     * 查询考试任务图片管理列表
     * 
     * @param examTaskPicture 考试任务图片管理
     * @return 考试任务图片管理集合
     */
    public List<ExamTaskPicture> selectExamTaskPictureList(ExamTaskPicture examTaskPicture);

    /**
     * 新增考试任务图片管理
     * 
     * @param examTaskPicture 考试任务图片管理
     * @return 结果
     */
    public int insertExamTaskPicture(ExamTaskPicture examTaskPicture);

    /**
     * 修改考试任务图片管理
     * 
     * @param examTaskPicture 考试任务图片管理
     * @return 结果
     */
    public int updateExamTaskPicture(ExamTaskPicture examTaskPicture);

    /**
     * 删除考试任务图片管理
     * 
     * @param photoCode 考试任务图片管理ID
     * @return 结果
     */
    public int deleteExamTaskPictureById(String photoCode);

    /**
     * 批量删除考试任务图片管理
     * 
     * @param photoCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteExamTaskPictureByIds(String[] photoCodes);
}
