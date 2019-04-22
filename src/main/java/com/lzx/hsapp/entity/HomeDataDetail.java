package com.lzx.hsapp.entity;

public class HomeDataDetail extends HomeData {
    private String expertSummary;
    private String courseSummary;

    @Override
    public String toString() {
        return "HomeDataDetail{" +
                "expertSummary='" + expertSummary + '\'' +
                ", courseSummary='" + courseSummary + '\'' +
                '}';
    }

    public String getExpertSummary() {
        return expertSummary;
    }

    public void setExpertSummary(String expertSummary) {
        this.expertSummary = expertSummary;
    }

    public String getCourseSummary() {
        return courseSummary;
    }

    public void setCourseSummary(String courseSummary) {
        this.courseSummary = courseSummary;
    }

}
