package com.lzx.hsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TripProgressDto implements Serializable {

    private static final long serialVersionUID = 3843841361631411743L;

    private String status;          //行程状态（0==等待邀请；1==接受邀请；2==食宿确定；3==材料上传）确定之后不能再更改

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date waitInvitationTime;        //等待邀请时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date acceptInvitationTime;          //接受邀请时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date boardAndLodgingTime;           //食宿确定时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date uploadTime;                //上传材料成功时间

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWaitInvitationTime() {
        return waitInvitationTime;
    }

    public void setWaitInvitationTime(Date waitInvitationTime) {
        this.waitInvitationTime = waitInvitationTime;
    }

    public Date getAcceptInvitationTime() {
        return acceptInvitationTime;
    }

    public void setAcceptInvitationTime(Date acceptInvitationTime) {
        this.acceptInvitationTime = acceptInvitationTime;
    }

    public Date getBoardAndLodgingTime() {
        return boardAndLodgingTime;
    }

    public void setBoardAndLodgingTime(Date boardAndLodgingTime) {
        this.boardAndLodgingTime = boardAndLodgingTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "TripProgressDto{" +
                "status='" + status + '\'' +
                ", waitInvitationTime=" + waitInvitationTime +
                ", acceptInvitationTime=" + acceptInvitationTime +
                ", boardAndLodgingTime=" + boardAndLodgingTime +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
