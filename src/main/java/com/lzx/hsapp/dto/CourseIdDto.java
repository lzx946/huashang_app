package com.lzx.hsapp.dto;

import java.io.Serializable;

public class CourseIdDto implements Serializable {

    private static final long serialVersionUID = 4515562472726140637L;

    private Integer courseId;       //课程ID

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseIdDto{" +
                "courseId=" + courseId +
                '}';
    }
}
