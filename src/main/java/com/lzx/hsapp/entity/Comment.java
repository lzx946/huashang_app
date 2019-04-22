package com.lzx.hsapp.entity;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer cid;

    private Integer uid;

    private Integer state;

    private Date createtime;

    private String content;
    private Integer iscommend;

    public Integer getIscommend() {
        return iscommend;
    }

    public void setIscommend(Integer iscommend) {
        this.iscommend = iscommend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}