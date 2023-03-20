package com.hig.fms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.fms.mapper.FmsPhotoMapper;
import com.hig.fms.domain.FmsPhoto;
import com.hig.fms.service.IFmsPhotoService;

/**
 * 图片管理Service业务层处理
 * 
 * @author qnsdt
 * @date 2021-12-14
 */
@Service
public class FmsPhotoServiceImpl implements IFmsPhotoService 
{
    @Autowired
    private FmsPhotoMapper fmsPhotoMapper;

    /**
     * 查询图片管理
     * 
     * @param photoId 图片管理ID
     * @return 图片管理
     */
    @Override
    public FmsPhoto selectFmsPhotoById(Long photoId)
    {
        return fmsPhotoMapper.selectFmsPhotoById(photoId);
    }

    /**
     * 查询图片管理列表
     * 
     * @param fmsPhoto 图片管理
     * @return 图片管理
     */
    @Override
    public List<FmsPhoto> selectFmsPhotoList(FmsPhoto fmsPhoto)
    {
        return fmsPhotoMapper.selectFmsPhotoList(fmsPhoto);
    }

    /**
     * 新增图片管理
     * 
     * @param fmsPhoto 图片管理
     * @return 结果
     */
    @Override
    public int insertFmsPhoto(FmsPhoto fmsPhoto)
    {
        return fmsPhotoMapper.insertFmsPhoto(fmsPhoto);
    }

    /**
     * 修改图片管理
     * 
     * @param fmsPhoto 图片管理
     * @return 结果
     */
    @Override
    public int updateFmsPhoto(FmsPhoto fmsPhoto)
    {
        return fmsPhotoMapper.updateFmsPhoto(fmsPhoto);
    }

    /**
     * 批量删除图片管理
     * 
     * @param photoIds 需要删除的图片管理ID
     * @return 结果
     */
    @Override
    public int deleteFmsPhotoByIds(Long[] photoIds)
    {
        return fmsPhotoMapper.deleteFmsPhotoByIds(photoIds);
    }

    /**
     * 删除图片管理信息
     * 
     * @param photoId 图片管理ID
     * @return 结果
     */
    @Override
    public int deleteFmsPhotoById(Long photoId)
    {
        return fmsPhotoMapper.deleteFmsPhotoById(photoId);
    }
}
