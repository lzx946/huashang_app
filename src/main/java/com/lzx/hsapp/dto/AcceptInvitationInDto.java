package com.lzx.hsapp.dto;

import java.io.Serializable;
import java.util.List;

public class AcceptInvitationInDto implements Serializable {

    private static final long serialVersionUID = -2277849866143833407L;

    private Integer tripId;     //行程ID

    private String agree;       //操作（1==同意；2==更改；3==取消开课）

    private List<ClassDto> classList;      //班次（教学点）

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public List<ClassDto> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassDto> classList) {
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "AcceptInvitationInDto{" +
                "tripId=" + tripId +
                ", agree='" + agree + '\'' +
                ", classList=" + classList +
                '}';
    }
}
