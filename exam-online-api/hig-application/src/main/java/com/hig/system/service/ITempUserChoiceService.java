package com.hig.system.service;

import java.util.List;
import com.hig.system.domain.TempUserChoice;

/**
 * 用户选择Service接口
 * 
 * @author qnsdt
 * @date 2023-01-31
 */
public interface ITempUserChoiceService 
{
    /**
     * 查询用户选择
     * 
     * @param choiceCode 用户选择ID
     * @return 用户选择
     */
    public TempUserChoice selectTempUserChoiceById(String choiceCode);

    /**
     * 查询用户选择列表
     * 
     * @param tempUserChoice 用户选择
     * @return 用户选择集合
     */
    public List<TempUserChoice> selectTempUserChoiceList(TempUserChoice tempUserChoice);

    /**
     * 新增用户选择
     * 
     * @param tempUserChoice 用户选择
     * @return 结果
     */
    public int insertTempUserChoice(TempUserChoice tempUserChoice);
    
    public int batchInsertTempUser(List<TempUserChoice> userList);

    /**
     * 修改用户选择
     * 
     * @param tempUserChoice 用户选择
     * @return 结果
     */
    public int updateTempUserChoice(TempUserChoice tempUserChoice);

    /**
     * 批量删除用户选择
     * 
     * @param choiceCodes 需要删除的用户选择ID
     * @return 结果
     */
    public int deleteTempUserChoice(List<TempUserChoice> userList);

}
