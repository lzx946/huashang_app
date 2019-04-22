package com.lzx.hsapp.entity;

import java.util.Date;

/**
 * Created by wangdaren on 2018/2/2.
 */
public class EnlistVoinfo extends Enlist {
    private String coursename;//课程名
    private String room;//地点
    private String classroom;//什么班
    private Date starttime;
    private Date stoptime;

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }
}
