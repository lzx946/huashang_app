package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class CourseDetailSingleDto implements Serializable {

    private static final long serialVersionUID = 2488711714216034276L;

    private Integer id;         //课程ID

    private String name;           //课程名称

    private Integer teacherId;      //教授ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;         //开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date stopTime;          //结束时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;            //创建时间

    private String summary;         //摘要

    private String realName;        //教授真实姓名

    private String academic;        //职称

    private String url;             //教授头像url

    private String employer;        //工作单位

    private String room;            //具体上课地点

    private String codeFlagName;        //教学点

    private String period;              //第几期

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCodeFlagName() {
        return codeFlagName;
    }

    public void setCodeFlagName(String codeFlagName) {
        this.codeFlagName = codeFlagName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "CourseDetailSingleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", createTime=" + createTime +
                ", summary='" + summary + '\'' +
                ", realName='" + realName + '\'' +
                ", academic='" + academic + '\'' +
                ", url='" + url + '\'' +
                ", employer='" + employer + '\'' +
                ", room='" + room + '\'' +
                ", codeFlagName='" + codeFlagName + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
