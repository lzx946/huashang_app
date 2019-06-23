package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ClassDto implements Serializable {

    private static final long serialVersionUID = -8599896357267106289L;

    private Integer codeFlag;       //教学点code

    private String codeFlagName;        //教学点名称

    private String city;

    private Integer id;

    private String place;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date startTime;         //开始时间

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date stopTime;          //结束时间

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    @Override
    public String toString() {
        return "ClassDto{" +
                "codeFlag=" + codeFlag +
                ", codeFlagName='" + codeFlagName + '\'' +
                ", city='" + city + '\'' +
                ", id=" + id +
                ", place='" + place + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                '}';
    }
}
