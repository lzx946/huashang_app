package com.lzx.hsapp.dto;

import java.io.Serializable;

public class TripFileDto implements Serializable {

    private static final long serialVersionUID = -8921626843147003297L;

    private Integer visaId;         //签证文件ID

    private String visaUrl;         //签证url

    private Integer planeTicketId;      //机票文件ID

    private String planeTicketUrl;          //机票url

    public Integer getVisaId() {
        return visaId;
    }

    public void setVisaId(Integer visaId) {
        this.visaId = visaId;
    }

    public String getVisaUrl() {
        return visaUrl;
    }

    public void setVisaUrl(String visaUrl) {
        this.visaUrl = visaUrl;
    }

    public Integer getPlaneTicketId() {
        return planeTicketId;
    }

    public void setPlaneTicketId(Integer planeTicketId) {
        this.planeTicketId = planeTicketId;
    }

    public String getPlaneTicketUrl() {
        return planeTicketUrl;
    }

    public void setPlaneTicketUrl(String planeTicketUrl) {
        this.planeTicketUrl = planeTicketUrl;
    }

    @Override
    public String toString() {
        return "TripFileDto{" +
                "visaId=" + visaId +
                ", visaUrl='" + visaUrl + '\'' +
                ", planeTicketId=" + planeTicketId +
                ", planeTicketUrl='" + planeTicketUrl + '\'' +
                '}';
    }
}
