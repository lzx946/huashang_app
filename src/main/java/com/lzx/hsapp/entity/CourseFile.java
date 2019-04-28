package com.lzx.hsapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 课程材料表
 */
@Entity
@Table(name = "course_file")
public class CourseFile {

    @Id
    private Integer id;             //课程文件材料表ID

    private String courseIds;       //多个课程ID（同一个课程不同教学点）

    private Integer fileId;             //文件材料ID

    private Date createTime;            //创建时间

    private Date modify_time;           //更新时间

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

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return "CourseFile{" +
                "id=" + id +
                ", courseIds='" + courseIds + '\'' +
                ", fileId=" + fileId +
                ", createTime=" + createTime +
                ", modify_time=" + modify_time +
                '}';
    }
}
