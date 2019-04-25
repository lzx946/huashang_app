package com.lzx.hsapp.utils;

public class WeiXinResult {

    public static final int NEWSMSG = 1;            //图文消息
    private boolean isSuccess;
    private Object obj;
    private int type;
    private String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "WeiXinResult{" +
                "isSuccess=" + isSuccess +
                ", obj=" + obj +
                ", type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
}
