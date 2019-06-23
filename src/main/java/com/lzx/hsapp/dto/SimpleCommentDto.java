package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class SimpleCommentDto implements Serializable {

    private static final long serialVersionUID = 5513865251761012447L;

    private Integer studentId;      //评论人ID

    private String realName;        //姓名

    private String PhotoUrl;        //头像

    private String content;         //评论内容

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date commentTime;       //评论时间

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "SimpleCommentDto{" +
                "studentId=" + studentId +
                ", realName='" + realName + '\'' +
                ", PhotoUrl='" + PhotoUrl + '\'' +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
