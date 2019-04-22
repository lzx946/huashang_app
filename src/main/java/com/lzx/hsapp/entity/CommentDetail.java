package com.lzx.hsapp.entity;

public class CommentDetail extends Comment {
    private String commentName;
    private String imgUrl;
    private String createTimeStr;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    @Override
    public String toString() {
        return "CommentDetail{" +
                "commentName='" + commentName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createTimeStr='" + createTimeStr + '\'' +
                '}';
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
