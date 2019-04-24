package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 邀请表
 */
@Entity
@Table(name = "invitation")
public class Invitation {

    @Id
    private Integer id;             //邀请表ID

    private Integer courseId;           //课程ID

    private String city;            //城市

    private String place;           //地点

    private Date startTime;         //开始时间

    private Date stopTime;          //结束时间

    private String agree;           //接受邀请（0==未操作；1==同意；2==更改；3==取消开课）

    private Date createTime;            //创建时间

    private Date modifyTime;            //修改时间

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
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
        return "Invitation{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", city='" + city + '\'' +
                ", place='" + place + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", agree='" + agree + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
