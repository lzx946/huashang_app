package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CourseDetailDto implements Serializable {

    private static final long serialVersionUID = 5809523122414013850L;

    private Integer courseId;           //课程ID

    private String courseName;              //课程名称

    private String period;              //第几期

    private String employer;            //工作单位

    private String realName;            //真实姓名

    private String academic;            //职称

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date totalBegin;            //总开课时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date totalEnd;              //总结课时间

    private List<TeachPointDto> teachPointList;         //教学点列表

    private Integer teacherId;          //教授ID

    private Integer photoId;            //教授头像ID

    private String photoUrl;                 //头像url

    private String summary;             //课程摘要

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
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

    public Date getTotalBegin() {
        return totalBegin;
    }

    public void setTotalBegin(Date totalBegin) {
        this.totalBegin = totalBegin;
    }

    public Date getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(Date totalEnd) {
        this.totalEnd = totalEnd;
    }

    public List<TeachPointDto> getTeachPointList() {
        return teachPointList;
    }

    public void setTeachPointList(List<TeachPointDto> teachPointList) {
        this.teachPointList = teachPointList;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "CourseDetailDto{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", period='" + period + '\'' +
                ", employer='" + employer + '\'' +
                ", realName='" + realName + '\'' +
                ", academic='" + academic + '\'' +
                ", totalBegin=" + totalBegin +
                ", totalEnd=" + totalEnd +
                ", teachPointList=" + teachPointList +
                ", teacherId=" + teacherId +
                ", photoId=" + photoId +
                ", photoUrl='" + photoUrl + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
