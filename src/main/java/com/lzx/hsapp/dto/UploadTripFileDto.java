package com.lzx.hsapp.dto;

import java.io.Serializable;

public class UploadTripFileDto implements Serializable {

    private static final long serialVersionUID = 7802762132204156188L;

    private Integer tripId;     //行程ID

    private Integer visaId;     //签证文件ID

    private Integer planeTicketId;      //机票文件ID

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getVisaId() {
        return visaId;
    }

    public void setVisaId(Integer visaId) {
        this.visaId = visaId;
    }

    public Integer getPlaneTicketId() {
        return planeTicketId;
    }

    public void setPlaneTicketId(Integer planeTicketId) {
        this.planeTicketId = planeTicketId;
    }

    @Override
    public String toString() {
        return "UploadTripFileDto{" +
                "tripId=" + tripId +
                ", visaId=" + visaId +
                ", planeTicketId=" + planeTicketId +
                '}';
    }
}
