package com.lzx.hsapp.dto;

import java.io.Serializable;

public class TripIdDto implements Serializable {

    private static final long serialVersionUID = -2024433961195533210L;

    private Integer tripId;         //行程ID

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    @Override
    public String toString() {
        return "TripIdDto{" +
                "tripId=" + tripId +
                '}';
    }
}
