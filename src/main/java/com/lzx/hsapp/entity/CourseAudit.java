package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *课程审核表
 */
@Entity
@Table(name = "course_audit")
public class CourseAudit {

    @Id
    private Integer id;             //课程审核表ID

    private String courseIds;           //多个课程ID（同一个课程不同教学点）

    private String status;          //课程状态（0==申请课程；1==审核；2==材料上传；3==开课；4==结课）

    private String firstAudit;              //初步审核（0==未审核；1==通过；2==不通过）

    private Date firstAuditTime;            //初步审核成功时间

    private String courseFile;              //课程文件材料审核（0==未审核；1==通过；2==不通过）

    private Date uploadTime;                //上传材料成功时间

    private Date startCourseTime;           //开课时间

    private Date stopCourseTime;            //结课时间

    private Date createTime;                //创建时间

    private Date modifyTime;                //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstAudit() {
        return firstAudit;
    }

    public void setFirstAudit(String firstAudit) {
        this.firstAudit = firstAudit;
    }

    public Date getFirstAuditTime() {
        return firstAuditTime;
    }

    public void setFirstAuditTime(Date firstAuditTime) {
        this.firstAuditTime = firstAuditTime;
    }

    public String getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(String courseFile) {
        this.courseFile = courseFile;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getStartCourseTime() {
        return startCourseTime;
    }

    public void setStartCourseTime(Date startCourseTime) {
        this.startCourseTime = startCourseTime;
    }

    public Date getStopCourseTime() {
        return stopCourseTime;
    }

    public void setStopCourseTime(Date stopCourseTime) {
        this.stopCourseTime = stopCourseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "CourseAudit{" +
                "id=" + id +
                ", courseIds='" + courseIds + '\'' +
                ", status='" + status + '\'' +
                ", firstAudit='" + firstAudit + '\'' +
                ", firstAuditTime=" + firstAuditTime +
                ", courseFile='" + courseFile + '\'' +
                ", uploadTime=" + uploadTime +
                ", startCourseTime=" + startCourseTime +
                ", stopCourseTime=" + stopCourseTime +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
