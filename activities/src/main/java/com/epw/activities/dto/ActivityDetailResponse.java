package com.epw.activities.dto;

public class ActivityDetailResponse {
    private Long id;
    private String place;
    private Integer estimatedMinutes;
    private String privateNotes;
    private Long activityId;

    public Long getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public String getPrivateNotes() {
        return privateNotes;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public void setPrivateNotes(String privateNotes) {
        this.privateNotes = privateNotes;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
