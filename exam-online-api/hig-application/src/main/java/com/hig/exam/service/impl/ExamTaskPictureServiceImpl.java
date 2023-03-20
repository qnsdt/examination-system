package com.hig.exam.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.exam.mapper.ExamTaskPictureMapper;
import com.hig.exam.domain.ExamTaskPicture;
import com.hig.exam.service.IExamTaskPictureService;

/**
 * 考试任务图片管理Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-01-18
 */
@Service
public class ExamTaskPictureServiceImpl implements IExamTaskPictureService 
{
    @Autowired
    private ExamTaskPictureMapper examTaskPictureMapper;

    /**
     * 查询考试任务图片管理
     * 
     * @param photoCode 考试任务图片管理ID
     * @return 考试任务图片管理
     */
    @Override
    public ExamTaskPicture selectExamTaskPictureById(String photoCode)
    {
        return examTaskPictureMapper.selectExamTaskPictureById(photoCode);
    }

    /**
     * 查询考试任务图片管理列表
     * 
     * @param examTaskPicture 考试任务图片管理
     * @return 考试任务图片管理
     */
    @Override
    public List<ExamTaskPicture> selectExamTaskPictureList(ExamTaskPicture examTaskPicture)
    {
        return examTaskPictureMapper.selectExamTaskPictureList(examTaskPicture);
    }

    /**
     * 新增考试任务图片管理
     * 
     * @param examTaskPicture 考试任务图片管理
     * @return 结果
     */
    @Override
    public int insertExamTaskPicture(ExamTaskPicture examTaskPicture)
    {
        examTaskPicture.setCreateTime(DateUtils.getNowDate());
        return examTaskPictureMapper.insertExamTaskPicture(examTaskPicture);
    }

    /**
     * 修改考试任务图片管理
     * 
     * @param examTaskPicture 考试任务图片管理
     * @return 结果
     */
    @Override
    public int updateExamTaskPicture(ExamTaskPicture examTaskPicture)
    {
        return examTaskPictureMapper.updateExamTaskPicture(examTaskPicture);
    }

    /**
     * 批量删除考试任务图片管理
     * 
     * @param photoCodes 需要删除的考试任务图片管理ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskPictureByIds(String[] photoCodes)
    {
        return examTaskPictureMapper.deleteExamTaskPictureByIds(photoCodes);
    }

    /**
     * 删除考试任务图片管理信息
     * 
     * @param photoCode 考试任务图片管理ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskPictureById(String photoCode)
    {
        return examTaskPictureMapper.deleteExamTaskPictureById(photoCode);
    }
}
