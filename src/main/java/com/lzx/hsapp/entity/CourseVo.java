package com.lzx.hsapp.entity;

import java.util.List;

/**
 * Created by wangdaren on 2018/3/25.
 */
public class CourseVo extends Course {
    private String realname;
    private String academic;
    private String url;
    private String employer;
    private String time;
    private String totalbegin;
    private String totalend;

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    /**
     * 结束时间戳
     */
    private Long endtime;

    public String getTotalbegin() {
        return totalbegin;
    }

    public void setTotalbegin(String totalbegin) {
        this.totalbegin = totalbegin;
    }

    public String getTotalend() {
        return totalend;
    }

    public void setTotalend(String totalend) {
        this.totalend = totalend;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    private List<CourseVo> courses;

    public List<CourseVo> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseVo> courses) {
        this.courses = courses;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
