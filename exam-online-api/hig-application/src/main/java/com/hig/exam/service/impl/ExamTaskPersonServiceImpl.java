package com.hig.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hig.exam.mapper.ExamTaskPersonMapper;
import com.hig.exam.domain.ExamTaskManager;
import com.hig.exam.domain.ExamTaskPerson;
import com.hig.exam.service.IExamTaskPersonService;

/**
 * 考试人员Service业务层处理
 * 
 * @author qnsdt
 * @date 2023-01-06
 */
@Service
public class ExamTaskPersonServiceImpl implements IExamTaskPersonService 
{
    @Autowired
    private ExamTaskPersonMapper examTaskPersonMapper;

    /**
     * 查询考试人员
     * 
     * @param examCode 考试人员ID
     * @return 考试人员
     */
    @Override
    public ExamTaskPerson selectExamTaskPersonById(String examCode)
    {
        return examTaskPersonMapper.selectExamTaskPersonById(examCode);
    }

    /**
     * 查询考试人员列表
     * 
     * @param examTaskPerson 考试人员
     * @return 考试人员
     */
    @Override
    public List<ExamTaskPerson> selectExamTaskPersonList(ExamTaskPerson examTaskPerson)
    {
        return examTaskPersonMapper.selectExamTaskPersonList(examTaskPerson);
    }

    /**
     * 新增考试人员
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    @Override
    public int insertExamTaskPerson(ExamTaskPerson examTaskPerson)
    {
        return examTaskPersonMapper.insertExamTaskPerson(examTaskPerson);
    }

    /**
     * 修改考试人员
     * 
     * @param examTaskPerson 考试人员
     * @return 结果
     */
    @Override
    public int updateExamTaskPerson(ExamTaskPerson examTaskPerson)
    {
        return examTaskPersonMapper.updateExamTaskPerson(examTaskPerson);
    }

    /**
     * 批量删除考试人员
     * 
     * @param examCodes 需要删除的考试人员ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskPersonByIds(String[] examCodes)
    {
        return examTaskPersonMapper.deleteExamTaskPersonByIds(examCodes);
    }

    /**
     * 删除考试人员信息
     * 
     * @param examCode 考试人员ID
     * @return 结果
     */
    @Override
    public int deleteExamTaskPersonById(String examCode)
    {
        return examTaskPersonMapper.deleteExamTaskPersonById(examCode);
    }

	@Override
	public int batchInsertPerson(String examCode) {
		// TODO Auto-generated method stub
		return examTaskPersonMapper.batchInsertPerson(examCode);
	}

	@Override
	public int updateStartTime(ExamTaskPerson examTaskPerson) {
		// TODO Auto-generated method stub
		ExamTaskPerson taskPerson = examTaskPersonMapper.selectExamTaskPerson(examTaskPerson);
		
		if (taskPerson != null && taskPerson.getStartTime() == null) {
			examTaskPersonMapper.updateStartTime(examTaskPerson);
		}
		return 1;
	}

	@Override
	public ExamTaskPerson selectExamTaskPerson(ExamTaskPerson examTaskPerson) {
		// TODO Auto-generated method stub
		return examTaskPersonMapper.selectExamTaskPerson(examTaskPerson);
	}

	@Override
	public int updateDoneStatus(ExamTaskPerson examTaskPerson) {
		// TODO Auto-generated method stub
		return examTaskPersonMapper.updateDoneStatus(examTaskPerson);
	}

	
}
