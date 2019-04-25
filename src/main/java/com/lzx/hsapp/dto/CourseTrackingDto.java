package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class CourseTrackingDto implements Serializable {

    private static final long serialVersionUID = 4255448118967939740L;

    private String status;         //课程状态（0==申请课程；1==审核；2==材料上传；3==开课；4==结课）

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date applyTime;             //申请课程时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date auditTime;             //审核成功时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date uploadTime;            //材料上传时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date courseStartTime;               //开课时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date courseStopTime;            //结课时间

    private String courseName;              //课程名称

    private String period;              //第几期
}
