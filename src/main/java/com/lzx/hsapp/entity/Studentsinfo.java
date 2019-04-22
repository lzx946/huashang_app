package com.lzx.hsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Studentsinfo implements Serializable {
    private Integer id;

    private String email;

    private String password;

    private Integer photoid;

    private String codeflagname;

    public String getCodeflagname() {
        return codeflagname;
    }

    public void setCodeflagname(String codeflagname) {
        this.codeflagname = codeflagname;
    }

    private String phone;

    private String account;

    private String realname;

    private String position;

    private Integer state;

    private Date createtime;

    private String companyurl;

    private String enname;

    private String otherposition;

    private String company;

    private String summary;

    private String motto;

    private Integer qrcode;

    private String wechat;

    private String studentid;

    private Integer area;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getQrcode() {
        return qrcode;
    }

    public void setQrcode(Integer qrcode) {
        this.qrcode = qrcode;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPhotoid() {
        return photoid;
    }

    public void setPhotoid(Integer photoid) {
        this.photoid = photoid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
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

    public String getCompanyurl() {
        return companyurl;
    }

    public void setCompanyurl(String companyurl) {
        this.companyurl = companyurl == null ? null : companyurl.trim();
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public String getOtherposition() {
        return otherposition;
    }

    public void setOtherposition(String otherposition) {
        this.otherposition = otherposition == null ? null : otherposition.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}