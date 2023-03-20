package com.hig.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.system.mapper.TempUserChoiceMapper;
import com.hig.system.domain.TempUserChoice;
import com.hig.system.service.ITempUserChoiceService;

/**
 * 用户选择Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-01-31
 */
@Service
public class TempUserChoiceServiceImpl implements ITempUserChoiceService 
{
    @Autowired
    private TempUserChoiceMapper tempUserChoiceMapper;

    /**
     * 查询用户选择
     * 
     * @param choiceCode 用户选择ID
     * @return 用户选择
     */
    @Override
    public TempUserChoice selectTempUserChoiceById(String choiceCode)
    {
        return tempUserChoiceMapper.selectTempUserChoiceById(choiceCode);
    }

    /**
     * 查询用户选择列表
     * 
     * @param tempUserChoice 用户选择
     * @return 用户选择
     */
    @Override
    public List<TempUserChoice> selectTempUserChoiceList(TempUserChoice tempUserChoice)
    {
        return tempUserChoiceMapper.selectTempUserChoiceList(tempUserChoice);
    }

    /**
     * 新增用户选择
     * 
     * @param tempUserChoice 用户选择
     * @return 结果
     */
    @Override
    public int insertTempUserChoice(TempUserChoice tempUserChoice)
    {
        return tempUserChoiceMapper.insertTempUserChoice(tempUserChoice);
    }

    /**
     * 修改用户选择
     * 
     * @param tempUserChoice 用户选择
     * @return 结果
     */
    @Override
    public int updateTempUserChoice(TempUserChoice tempUserChoice)
    {
        return tempUserChoiceMapper.updateTempUserChoice(tempUserChoice);
    }

	@Override
	public int deleteTempUserChoice(List<TempUserChoice> userList) {
		// TODO Auto-generated method stub
		int count = 0;
		for(TempUserChoice tempUser: userList) {
			count += tempUserChoiceMapper.deleteTempUserChoice(tempUser);
		}
		return count;
	}

	@Override
	public int batchInsertTempUser(List<TempUserChoice> userList) {
		// TODO Auto-generated method stub
		int count = 0;
		for(TempUserChoice tempUser: userList) {
			count += tempUserChoiceMapper.insertTempUserChoice(tempUser);
		}
		return count;
	}

}
