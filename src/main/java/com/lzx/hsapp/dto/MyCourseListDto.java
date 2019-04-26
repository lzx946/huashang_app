package com.lzx.hsapp.dto;

import java.io.Serializable;

public class MyCourseListDto implements Serializable {

    private static final long serialVersionUID = -6126415506660312284L;

    private Integer studentId;      //学生ID

    private Integer pageNum;        //当前页码

    private Integer pageSize;           //每页包含的记录数

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
        return "MyCourseListDto{" +
                "studentId=" + studentId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
