package com.hig.onlineexam.domain.dto;

import java.util.Date;

import lombok.Data;


@Data
public class ExamTitleData {
    /** 考试序号 */
    private Long examId;

    /** 考试代码 */
    private String examCode;

    /** 考试名称 */
    private String examName;

    /** 考试说明 */
    private String examDescribe;

    /** 组卷方式 */
    private String buildType;

    /** 强制抽卷 */
    private String forceDone;

    /** 考试题库 */
    private String examBank;

    /** 考试题库文字 */
    private String examBankText;


    /** 图片链接 */
    private String pictureUrl;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;
    
    /** 当前日期 */
    private Date currentDate;
	/** 考试时长 */
    private String examDuration;
    
    private int diffTime;

    /** 判断题数 */
    private int judgeNumber;
    
    /** 单选题数 */
    private int radioNumber;
    
    /** 多选题数 */
    private int choiceNumber;
    
    private int questionsScore;
    /** 状态 */
    private Long status;
    
    /** 创建人 */
    private String createBy; 
    
    /** 创建部门 */
    private int createDept; 
    
    /** 创建时间 */
    private Date createTime;
}
