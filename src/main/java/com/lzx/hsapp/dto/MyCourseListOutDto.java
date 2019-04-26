package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzx.hsapp.utils.PageUtil;

import java.io.Serializable;
import java.util.Date;

public class MyCourseListOutDto implements Serializable {

    private static final long serialVersionUID = 714966708529361655L;

    private Integer courseId;             //课程ID

    private Integer enlistId;           //报名表ID

    private String name;        //课程题目

    private String period;              //第几期

    private Integer codeFlag;       //教学点code

    private String codeFlagName;        //教学点名称

    private String room;                //具体开课地点

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date startTime;         //开始时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date stopTime;          //结束时间

    private String realName;            //教授真实姓名

    private Integer photoId;            //教授头像

    private String photoUrl;            //教授头像url

    private String academic;            //职称

    private String employer;            //工作单位

    private PageUtil page;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getEnlistId() {
        return enlistId;
    }

    public void setEnlistId(Integer enlistId) {
        this.enlistId = enlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getCodeFlag() {
        return codeFlag;
    }

    public void setCodeFlag(Integer codeFlag) {
        this.codeFlag = codeFlag;
    }

    public String getCodeFlagName() {
        return codeFlagName;
    }

    public void setCodeFlagName(String codeFlagName) {
        this.codeFlagName = codeFlagName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public PageUtil getPage() {
        return page;
    }

    public void setPage(PageUtil page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "MyCourseListOutDto{" +
                "courseId=" + courseId +
                ", enlistId=" + enlistId +
                ", name='" + name + '\'' +
                ", period='" + period + '\'' +
                ", codeFlag=" + codeFlag +
                ", codeFlagName='" + codeFlagName + '\'' +
                ", room='" + room + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", realName='" + realName + '\'' +
                ", photoId=" + photoId +
                ", photoUrl='" + photoUrl + '\'' +
                ", academic='" + academic + '\'' +
                ", employer='" + employer + '\'' +
                ", page=" + page +
                '}';
    }
}
