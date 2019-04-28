package com.lzx.hsapp.util.mapUtil;

public class UserDetails {

    private String place_id;
    private String formatted_address;
    private String long_name;
    private String short_name;
    private String types;
    private String geometry;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "place_id='" + place_id + '\'' +
                ", formatted_address='" + formatted_address + '\'' +
                ", long_name='" + long_name + '\'' +
                ", short_name='" + short_name + '\'' +
                ", types='" + types + '\'' +
                ", geometry='" + geometry + '\'' +
                '}';
    }
}
