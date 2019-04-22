package com.lzx.hsapp.entity;

public class SysDictionary {
    private Integer id;

    private Integer code;

    private Integer codeflag;

    private String codeflagname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCodeflag() {
        return codeflag;
    }

    public void setCodeflag(Integer codeflag) {
        this.codeflag = codeflag;
    }

    public String getCodeflagname() {
        return codeflagname;
    }

    public void setCodeflagname(String codeflagname) {
        this.codeflagname = codeflagname == null ? null : codeflagname.trim();
    }
}