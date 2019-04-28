package com.lzx.hsapp.entity.msg.request;

public class ImageMessage extends BaseMessage {

    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "picUrl='" + picUrl + '\'' +
                '}';
    }
}
