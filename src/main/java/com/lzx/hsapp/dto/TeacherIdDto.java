package com.lzx.hsapp.dto;

import java.io.Serializable;

public class TeacherIdDto implements Serializable {

    private static final long serialVersionUID = 5571602988639108442L;

    private Integer teacherId;      //教授ID

    private Integer pageNum;        //当前页码

    private Integer pageSize;           //每页包含的记录数

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "TeacherIdDto{" +
                "teacherId=" + teacherId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
