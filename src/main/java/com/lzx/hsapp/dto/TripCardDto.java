package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TripCardDto implements Serializable {

    private static final long serialVersionUID = 5481372161476405291L;

    private Integer tripId;     //行程ID

    private String name;        //课程题目

    private String status;         //行程状态（0==等待邀请；1==接受邀请；2==食宿确认；3==材料上传）

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date totalStartTime;         //开始时间

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date totalStopTime;          //结束时间

    private String period;              //第几期

    private String summary;             //摘要

    List<TeachPointDto> teachPointList;     //教学点

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
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

    @Override
    public String toString() {
        return "TripCardDto{" +
                "tripId=" + tripId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", totalStartTime=" + totalStartTime +
                ", totalStopTime=" + totalStopTime +
                ", period='" + period + '\'' +
                ", summary='" + summary + '\'' +
                ", teachPointList=" + teachPointList +
                '}';
    }
}
