package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 食宿表
 */
@Entity
@Table(name = "board_and_lodging")
public class BoardAndLodging {

    @Id
    private Integer id;             //食宿表ID

    private Integer tripId;         //行程跟踪表ID

    private String city;            //城市

    private String place;           //地点

    private Date startTime;         //开始时间

    private Date stopTime;          //结束时间

    private Date createTime;            //创建时间

    private Date modifyTime;            //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
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
        return "BoardAndLodging{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", city='" + city + '\'' +
                ", place='" + place + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
