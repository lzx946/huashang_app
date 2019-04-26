package com.lzx.hsapp.entity;

public class WeiXinQRCode {

    private String ticket;

    private int expire_seconds;

    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(int expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WeiXinQRCode{" +
                "ticket='" + ticket + '\'' +
                ", expire_seconds=" + expire_seconds +
                ", url='" + url + '\'' +
                '}';
    }
}
