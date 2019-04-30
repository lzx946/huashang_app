package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 行程材料手续表
 */
@Entity
@Table(name = "procedure")
public class Procedure {

    @Id
    private Integer id;             //行程材料手续表ID

    private Integer tripId;         //行程跟踪表ID

    private Integer visa;            //签证

    private Integer planeTicket;         //机票

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

    public Integer getVisa() {
        return visa;
    }

    public void setVisa(Integer visa) {
        this.visa = visa;
    }

    public Integer getPlaneTicket() {
        return planeTicket;
    }

    public void setPlaneTicket(Integer planeTicket) {
        this.planeTicket = planeTicket;
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
        return "Procedure{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", visa='" + visa + '\'' +
                ", planeTicket='" + planeTicket + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
