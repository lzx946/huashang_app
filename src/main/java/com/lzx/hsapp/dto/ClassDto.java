package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class ClassDto implements Serializable {

    private static final long serialVersionUID = -8599896357267106289L;

    private Integer codeFlag;       //教学点code

    private String codeFlagName;        //教学点名称

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;         //开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                '}';
    }
}
