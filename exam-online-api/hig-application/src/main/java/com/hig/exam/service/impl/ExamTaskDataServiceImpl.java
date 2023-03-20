package com.hig.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hig.exam.domain.ExamTaskData;
import com.hig.exam.domain.ExamTaskGroup;
import com.hig.exam.domain.ExamTaskManager;
import com.hig.exam.domain.ExamTaskQuestions;
import com.hig.exam.service.IExamTaskDataService;
import com.hig.exam.service.IExamTaskGroupService;
import com.hig.exam.service.IExamTaskManagerService;
import com.hig.exam.service.IExamTaskPersonService;
import com.hig.exam.service.IExamTaskQuestionsService;

@Service
public class ExamTaskDataServiceImpl implements IExamTaskDataService {
    @Autowired
    private IExamTaskGroupService examTaskGroupService;

    @Autowired
    private IExamTaskManagerService examTaskManagerService;

    @Autowired
    private IExamTaskQuestionsService examTaskQuestionsService;

    @Autowired
    private IExamTaskPersonService examTaskPersonService;


	@Override
	public int saveData(ExamTaskData examTaskData) throws Exception {
		// TODO Auto-generated method stub
		ExamTaskManager examTaskManager = examTaskData.getExamTaskManager();
		
		int count = 0;
		try
		{
			// 安全删除
			examTaskManagerService.deleteExamTaskManagerById(examTaskManager.getExamCode());
			
			// 保存数据
			count = examTaskManagerService.insertExamTaskManager(examTaskManager);
		}
		catch(Exception e) {
			throw new Exception("保存属性出错,信息:" + e.getMessage());
		}
		
		List<ExamTaskQuestions> questionsList = examTaskData.getTaskQuestionsList();
		
		// 安全删除
		examTaskQuestionsService.deleteExamTaskQuestionsById(examTaskManager.getExamCode());
		
		for(ExamTaskQuestions examTaskQuestions: questionsList) {
			try
			{
				count = examTaskQuestionsService.insertExamTaskQuestions(examTaskQuestions);
			}
			catch(Exception e) {
				throw new Exception("保存题目信息出错,信息:" + e.getMessage());				
			}
		}
		
		List<ExamTaskGroup> groupList = examTaskData.getTaskGroupList();

		// 安全删除
		examTaskGroupService.deleteExamTaskGroupById(examTaskManager.getExamCode());
		
		for (ExamTaskGroup examTaskGroup: groupList) {
			try
			{
				count = examTaskGroupService.insertExamTaskGroup(examTaskGroup);
			}
			catch(Exception e) {
				throw new Exception("保存分组信息出错,信息:" + e.getMessage());								
			}
		}
		
		// 安全删除
		examTaskPersonService.deleteExamTaskPersonById(examTaskManager.getExamCode());
		
		try
		{
			count = examTaskPersonService.batchInsertPerson(examTaskManager.getExamCode());
		}
		catch(Exception e) {
			throw new Exception("保存考试人员信息出错,信息:" + e.getMessage());											
		}
		
		return 1;
	}

}
