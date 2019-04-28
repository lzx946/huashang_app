package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CourseTrackingDto implements Serializable {

    private static final long serialVersionUID = 4255448118967939740L;

    //审核流程

    private String status;         //课程状态（0==申请课程；1==审核；2==材料上传；3==开课；4==结课）

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date applyTime;             //申请课程时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date auditSuccessTime;             //审核成功时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date uploadTime;            //材料上传时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date courseStartTime;               //开课时间

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date courseStopTime;            //结课时间

    //课程详细信息

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

    //审核结果
    private String auditMessage;            //审核结果消息

    private String auditor;              //审核人

    @JsonFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private Date auditTime;             //审核时间

    //材料上传

    List<FileDto> fileList;      //文件材料列表

    //评论列表

    List<SimpleCommentDto> commentList;     //评论列表

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAuditSuccessTime() {
        return auditSuccessTime;
    }

    public void setAuditSuccessTime(Date auditSuccessTime) {
        this.auditSuccessTime = auditSuccessTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setCourseStopTime(Date courseStopTime) {
        this.courseStopTime = courseStopTime;
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

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public List<FileDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileDto> fileList) {
        this.fileList = fileList;
    }

    public List<SimpleCommentDto> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<SimpleCommentDto> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "CourseTrackingDto{" +
                "status='" + status + '\'' +
                ", applyTime=" + applyTime +
                ", auditSuccessTime=" + auditSuccessTime +
                ", uploadTime=" + uploadTime +
                ", courseStartTime=" + courseStartTime +
                ", courseStopTime=" + courseStopTime +
                ", courseId=" + courseId +
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
                ", auditMessage='" + auditMessage + '\'' +
                ", auditor='" + auditor + '\'' +
                ", auditTime=" + auditTime +
                ", fileList=" + fileList +
                ", commentList=" + commentList +
                '}';
    }
}
