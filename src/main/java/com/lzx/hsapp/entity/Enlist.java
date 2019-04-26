package com.lzx.hsapp.entity;

import java.util.Date;

public class Enlist {
    private Integer id;

    private Integer cid;

    private Integer uid;

    private Integer result;

    private String signIn;              //签到（0==未签到；1==签到）

    private Date createtime;

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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Enlist{" +
                "id=" + id +
                ", cid=" + cid +
                ", uid=" + uid +
                ", result=" + result +
                ", signIn='" + signIn + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}