package com.lzx.hsapp.entity;

import java.util.List;

/**
 * Created by wangdaren on 2018/2/1.
 */
public class ExpertsVoinfo extends Expertsinfo {
    private String url;

    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
