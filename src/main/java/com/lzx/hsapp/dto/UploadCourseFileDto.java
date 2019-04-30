package com.lzx.hsapp.dto;

import java.io.Serializable;
import java.util.List;

public class UploadCourseFileDto implements Serializable {

    private static final long serialVersionUID = 4218517338287990724L;

    private Integer courseId;

    private List<Integer> fileIdList;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<Integer> getFileIdList() {
        return fileIdList;
    }

    public void setFileIdList(List<Integer> fileIdList) {
        this.fileIdList = fileIdList;
    }

    @Override
    public String toString() {
        return "UploadCourseFileDto{" +
                "courseId=" + courseId +
                ", fileIdList=" + fileIdList +
                '}';
    }
}
