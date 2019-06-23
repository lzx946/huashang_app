package com.lzx.hsapp.entity;

public class SysDictionary {
    private Integer id;

    private Integer code;

    private Integer codeflag;

    private String codeflagname;

    private double latitude;        //纬度

    private double longitude;       //经度

    private String englishName;     //英文名

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}