package com.lzx.hsapp.entity;

import java.util.Date;

public class Expertsinfo {
    private Integer id;

    private String email;

    private String password;

    private Integer photoid;

    private String phone;

    private String account;

    private String realname;

    private String academic;

    private Integer state;

    private Date createtime;

    private Integer isvisible;

    private String enname;

    private String openemail;

    private String employer;

    private String summary;

    private String otheracademic;

    private String motto;
    private String summarys;
    private Integer iscommend;

    public String getSummarys() {
        return summarys;
    }

    public void setSummarys(String summarys) {
        this.summarys = summarys;
    }

    public Integer getIscommend() {
        return iscommend;
    }

    public void setIscommend(Integer iscommend) {
        this.iscommend = iscommend;
    }

    public String getOtheracademic() {
        return otheracademic;
    }

    public void setOtheracademic(String otheracademic) {
        this.otheracademic = otheracademic;
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

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic == null ? null : academic.trim();
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

    public Integer getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(Integer isvisible) {
        this.isvisible = isvisible;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public String getOpenemail() {
        return openemail;
    }

    public void setOpenemail(String openemail) {
        this.openemail = openemail == null ? null : openemail.trim();
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer == null ? null : employer.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}