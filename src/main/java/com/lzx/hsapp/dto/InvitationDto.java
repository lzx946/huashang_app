package com.lzx.hsapp.dto;

import java.io.Serializable;
import java.util.List;

public class InvitationDto implements Serializable {

    private static final long serialVersionUID = -5833665190043935347L;

    private String agree;           //邀请状态（0==未操作；1==同意；2==更改；3==取消开课）

    private List<TripDetailDto> inviteList;     //邀请表显示地点列表

    private String message;         //提示消息

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public List<TripDetailDto> getInviteList() {
        return inviteList;
    }

    public void setInviteList(List<TripDetailDto> inviteList) {
        this.inviteList = inviteList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InvitationDto{" +
                "agree='" + agree + '\'' +
                ", inviteList=" + inviteList +
                ", message='" + message + '\'' +
                '}';
    }
}
