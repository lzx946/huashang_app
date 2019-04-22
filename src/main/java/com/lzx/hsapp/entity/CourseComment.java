package com.lzx.hsapp.entity;

public class CourseComment {
    private String imgUrl;
    private String commentName;
    private String commentContent;
    private String commentTime;

    @Override
    public String toString() {
        return "CourseComment{" +
                "imgUrl='" + imgUrl + '\'' +
                ", commentName='" + commentName + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime='" + commentTime + '\'' +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
