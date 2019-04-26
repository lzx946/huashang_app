package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "course_qr_code")
public class CourseQRCode {

    @Id
    private Integer id;         //课程报名二维码表ID

    private Integer courseId;          //课程ID

    private Integer qrCode;             //二维码文件表ID

    private Date createTime;            //创建时间

    private Date modifyTime;                //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getQrCode() {
        return qrCode;
    }

    public void setQrCode(Integer qrCode) {
        this.qrCode = qrCode;
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
        return "CourseQRCode{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", qrCode=" + qrCode +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
