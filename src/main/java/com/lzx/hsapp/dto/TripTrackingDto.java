package com.lzx.hsapp.dto;

import java.io.Serializable;
import java.util.List;

public class TripTrackingDto implements Serializable {

    private static final long serialVersionUID = 4687373676644608927L;

    private Integer tripId;             //行程ID

    private TripProgressDto tripProgress;           //行程进程

    private CourseDetailDto courseDetail;           //课程信息详情

    private String invitationAgree;     //接受邀请状态（0==未操作；1==同意；2==更改；3==取消开课）

    private String invitationMessage;       //接受邀请提示消息

    private List<InvitationDto> invitationList;               //邀请表列表

    private String needBoardAndLodging;     //是否需要食宿（0==未确认；1==需要；2==不需要）

    private String boardAndLodgingMessage;          //食宿提示消息

    private List<TripDetailDto> boardAndLodgingList;         //食宿表

    private TripFileDto tripFile;               //行程手续

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public TripProgressDto getTripProgress() {
        return tripProgress;
    }

    public void setTripProgress(TripProgressDto tripProgress) {
        this.tripProgress = tripProgress;
    }

    public CourseDetailDto getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(CourseDetailDto courseDetail) {
        this.courseDetail = courseDetail;
    }

    public String getInvitationAgree() {
        return invitationAgree;
    }

    public void setInvitationAgree(String invitationAgree) {
        this.invitationAgree = invitationAgree;
    }

    public String getInvitationMessage() {
        return invitationMessage;
    }

    public void setInvitationMessage(String invitationMessage) {
        this.invitationMessage = invitationMessage;
    }

    public List<InvitationDto> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<InvitationDto> invitationList) {
        this.invitationList = invitationList;
    }

    public String getNeedBoardAndLodging() {
        return needBoardAndLodging;
    }

    public void setNeedBoardAndLodging(String needBoardAndLodging) {
        this.needBoardAndLodging = needBoardAndLodging;
    }

    public String getBoardAndLodgingMessage() {
        return boardAndLodgingMessage;
    }

    public void setBoardAndLodgingMessage(String boardAndLodgingMessage) {
        this.boardAndLodgingMessage = boardAndLodgingMessage;
    }

    public List<TripDetailDto> getBoardAndLodgingList() {
        return boardAndLodgingList;
    }

    public void setBoardAndLodgingList(List<TripDetailDto> boardAndLodgingList) {
        this.boardAndLodgingList = boardAndLodgingList;
    }

    public TripFileDto getTripFile() {
        return tripFile;
    }

    public void setTripFile(TripFileDto tripFile) {
        this.tripFile = tripFile;
    }

    @Override
    public String toString() {
        return "TripTrackingDto{" +
                "tripId=" + tripId +
                ", tripProgress=" + tripProgress +
                ", courseDetail=" + courseDetail +
                ", invitationAgree='" + invitationAgree + '\'' +
                ", invitationMessage='" + invitationMessage + '\'' +
                ", invitationList=" + invitationList +
                ", needBoardAndLodging='" + needBoardAndLodging + '\'' +
                ", boardAndLodgingMessage='" + boardAndLodgingMessage + '\'' +
                ", boardAndLodgingList=" + boardAndLodgingList +
                ", tripFile=" + tripFile +
                '}';
    }
}
