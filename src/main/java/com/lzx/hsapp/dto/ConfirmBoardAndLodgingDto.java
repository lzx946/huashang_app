package com.lzx.hsapp.dto;

import java.io.Serializable;

public class ConfirmBoardAndLodgingDto implements Serializable {

    private static final long serialVersionUID = -4001377877530434442L;

    private Integer tripId;         //行程ID

    private String need;            //食宿确认（0==不需要；1==需要）

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    @Override
    public String toString() {
        return "ConfirmBoardAndLodgingDto{" +
                "tripId=" + tripId +
                ", need='" + need + '\'' +
                '}';
    }
}
