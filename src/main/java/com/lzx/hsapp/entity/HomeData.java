package com.lzx.hsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class HomeData implements Serializable{
    private String courseName;
    private Integer courseId;
    private String stage;
    private Integer expertId;
    private String ban;
    private String courseLocal;
    private Date courseStartTime;
    private Date courseStopTime;
    private String courseStartTimeStr;
    private String courseStopTimeStr;
    private String expertName;
    private String expertAcademic;
    private String imgUrl;
    private String codeflagname;
    private Integer iscommend;

    public Integer getIscommend() {
        return iscommend;
    }

    public void setIscommend(Integer iscommend) {
        this.iscommend = iscommend;
    }

    public String getCodeflagname() {
        return codeflagname;
    }

    public void setCodeflagname(String codeflagname) {
        this.codeflagname = codeflagname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "HomeData{" +
                "courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                ", stage='" + stage + '\'' +
                ", expertId=" + expertId +
                ", ban='" + ban + '\'' +
                ", courseLocal='" + courseLocal + '\'' +
                ", courseStartTime=" + courseStartTime +
                ", courseStopTime=" + courseStopTime +
                ", courseStartTimeStr='" + courseStartTimeStr + '\'' +
                ", courseStopTimeStr='" + courseStopTimeStr + '\'' +
                ", expertName='" + expertName + '\'' +
                ", expertAcademic='" + expertAcademic + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getCourseLocal() {
        return courseLocal;
    }

    public void setCourseLocal(String courseLocal) {
        this.courseLocal = courseLocal;
    }

    public Date getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(Date courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public Date getCourseStopTime() {
        return courseStopTime;
    }

    public void setCourseStopTime(Date courseStopTime) {
        this.courseStopTime = courseStopTime;
    }

    public String getCourseStartTimeStr() {
        return courseStartTimeStr;
    }

    public void setCourseStartTimeStr(String courseStartTimeStr) {
        this.courseStartTimeStr = courseStartTimeStr;
    }

    public String getCourseStopTimeStr() {
        return courseStopTimeStr;
    }

    public void setCourseStopTimeStr(String courseStopTimeStr) {
        this.courseStopTimeStr = courseStopTimeStr;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertAcademic() {
        return expertAcademic;
    }

    public void setExpertAcademic(String expertAcademic) {
        this.expertAcademic = expertAcademic;
    }
}
