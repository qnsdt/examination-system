package com.hig.fms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.fms.mapper.FmsFilesMapper;
import com.hig.fms.domain.FmsFiles;
import com.hig.fms.service.IFmsFilesService;

/**
 * 文件管理Service业务层处理
 * 
 * @author qnsdt
 * @date 2021-12-17
 */
@Service
public class FmsFilesServiceImpl implements IFmsFilesService 
{
    @Autowired
    private FmsFilesMapper fmsFilesMapper;

    /**
     * 查询文件管理
     * 
     * @param fileId 文件管理ID
     * @return 文件管理
     */
    @Override
    public FmsFiles selectFmsFilesById(Long fileId)
    {
        return fmsFilesMapper.selectFmsFilesById(fileId);
    }

    /**
     * 查询文件管理列表
     * 
     * @param fmsFiles 文件管理
     * @return 文件管理
     */
    @Override
    public List<FmsFiles> selectFmsFilesList(FmsFiles fmsFiles)
    {
        return fmsFilesMapper.selectFmsFilesList(fmsFiles);
    }

    /**
     * 新增文件管理
     * 
     * @param fmsFiles 文件管理
     * @return 结果
     */
    @Override
    public int insertFmsFiles(FmsFiles fmsFiles)
    {
        return fmsFilesMapper.insertFmsFiles(fmsFiles);
    }

    /**
     * 修改文件管理
     * 
     * @param fmsFiles 文件管理
     * @return 结果
     */
    @Override
    public int updateFmsFiles(FmsFiles fmsFiles)
    {
        return fmsFilesMapper.updateFmsFiles(fmsFiles);
    }

    /**
     * 批量删除文件管理
     * 
     * @param fileIds 需要删除的文件管理ID
     * @return 结果
     */
    @Override
    public int deleteFmsFilesByIds(Long[] fileIds)
    {
        return fmsFilesMapper.deleteFmsFilesByIds(fileIds);
    }

    /**
     * 删除文件管理信息
     * 
     * @param fileId 文件管理ID
     * @return 结果
     */
    @Override
    public int deleteFmsFilesById(Long fileId)
    {
        return fmsFilesMapper.deleteFmsFilesById(fileId);
    }
}
