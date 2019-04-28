package com.lzx.hsapp.dto;

import java.io.Serializable;
import java.util.List;

public class TripTrackingDto implements Serializable {

    private static final long serialVersionUID = 4687373676644608927L;

    private Integer tripId;             //行程ID

    private TripProgressDto tripProgress;           //行程进程

    private CourseDetailDto courseDetail;           //课程信息详情

    private List<InvitationDto> invitationList;               //邀请表列表

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

    public List<InvitationDto> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<InvitationDto> invitationList) {
        this.invitationList = invitationList;
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
                ", invitationList=" + invitationList +
                ", boardAndLodgingList=" + boardAndLodgingList +
                ", tripFile=" + tripFile +
                '}';
    }
}
