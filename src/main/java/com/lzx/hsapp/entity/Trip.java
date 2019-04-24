package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 行程跟踪表
 */
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    private Integer id;             //行程跟踪表ID

    private String courseIds;           //多个课程ID（同一个课程不同的教学点）

    private Integer teacherId;          //教授ID

    private String agree;                   //接受邀请（0==未确认；1==接受；2==更改时间；3==取消开课）

    private String boardAndLodging;         //食宿确认（0==未确认；1==需要；2==不需要）

    private String procedure;               //行程手续和材料（0==未审核；1==通过；2==不通过）

    private String status;              //行程跟踪状态（0==等待邀请；1==接受邀请；2==食宿确定；3==材料上传）

    private Date createTime;            //创建时间

    private Date modifyTime;            //修改时间

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getBoardAndLodging() {
        return boardAndLodging;
    }

    public void setBoardAndLodging(String boardAndLodging) {
        this.boardAndLodging = boardAndLodging;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Trip{" +
                "id=" + id +
                ", courseIds='" + courseIds + '\'' +
                ", teacherId=" + teacherId +
                ", agree='" + agree + '\'' +
                ", boardAndLodging='" + boardAndLodging + '\'' +
                ", procedure='" + procedure + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
