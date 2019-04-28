package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzx.hsapp.utils.PageUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GetCourseByTeacherIdOutDto implements Serializable {

    private static final long serialVersionUID = -8354702571770131887L;

    private Integer id;

    private String name;        //题目

    private String status;         //课程状态（0==申请课程；1==审核；2==材料上传；3==开课；4==结课）

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date totalStartTime;         //开始时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date totalStopTime;          //结束时间

    private String period;              //第几期

    private String summary;             //摘要

    List<TeachPointDto> teachPointList;

    PageUtil page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTotalStartTime() {
        return totalStartTime;
    }

    public void setTotalStartTime(Date totalStartTime) {
        this.totalStartTime = totalStartTime;
    }

    public Date getTotalStopTime() {
        return totalStopTime;
    }

    public void setTotalStopTime(Date totalStopTime) {
        this.totalStopTime = totalStopTime;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<TeachPointDto> getTeachPointList() {
        return teachPointList;
    }

    public void setTeachPointList(List<TeachPointDto> teachPointList) {
        this.teachPointList = teachPointList;
    }

    public PageUtil getPage() {
        return page;
    }

    public void setPage(PageUtil page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "GetCourseByTeacherIdOutDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", totalStartTime=" + totalStartTime +
                ", totalStopTime=" + totalStopTime +
                ", period='" + period + '\'' +
                ", summary='" + summary + '\'' +
                ", teachPointList=" + teachPointList +
                ", page=" + page +
                '}';
    }
}
