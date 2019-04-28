package com.lzx.hsapp.entity.msg.request;

public class TextMessage extends BaseMessage {

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "TextResponseMessage{" +
                "Content='" + Content + '\'' +
                '}';
    }
}
