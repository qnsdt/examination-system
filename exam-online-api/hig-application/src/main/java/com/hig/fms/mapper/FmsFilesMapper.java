package com.hig.fms.mapper;

import java.util.List;
import com.hig.fms.domain.FmsFiles;

/**
 * 文件管理Mapper接口
 * 
 * @author qnsdt
 * @date 2021-12-17
 */
public interface FmsFilesMapper 
{
    /**
     * 查询文件管理
     * 
     * @param fileId 文件管理ID
     * @return 文件管理
     */
    public FmsFiles selectFmsFilesById(Long fileId);

    /**
     * 查询文件管理列表
     * 
     * @param fmsFiles 文件管理
     * @return 文件管理集合
     */
    public List<FmsFiles> selectFmsFilesList(FmsFiles fmsFiles);

    /**
     * 新增文件管理
     * 
     * @param fmsFiles 文件管理
     * @return 结果
     */
    public int insertFmsFiles(FmsFiles fmsFiles);

    /**
     * 修改文件管理
     * 
     * @param fmsFiles 文件管理
     * @return 结果
     */
    public int updateFmsFiles(FmsFiles fmsFiles);

    /**
     * 删除文件管理
     * 
     * @param fileId 文件管理ID
     * @return 结果
     */
    public int deleteFmsFilesById(Long fileId);

    /**
     * 批量删除文件管理
     * 
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFmsFilesByIds(Long[] fileIds);
}
