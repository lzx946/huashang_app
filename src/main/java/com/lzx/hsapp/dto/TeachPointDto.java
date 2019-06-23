package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TeachPointDto implements Serializable {
    private static final long serialVersionUID = -8764222575400744594L;

    private Integer courseId;           //课程ID

    private Integer codeFlag;       //教学点code

    private String codeFlagName;        //教学点名称

    private String room;                //具体开课地点

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;         //开始时间

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date stopTime;          //结束时间

    private Integer state;              //状态0==未审核；1==审核通过；2==审核不通过；3==封禁；4==暂存状态

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TeachPointDto{" +
                "courseId=" + courseId +
                ", codeFlag=" + codeFlag +
                ", codeFlagName='" + codeFlagName + '\'' +
                ", room='" + room + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", state=" + state +
                '}';
    }
}
