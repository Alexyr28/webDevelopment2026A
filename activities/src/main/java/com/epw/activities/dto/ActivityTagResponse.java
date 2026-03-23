package com.epw.activities.dto;

import java.util.ArrayList;
import java.util.List;

public class ActivityTagResponse {
    private Long activityId;
    private String title;
    private List<TagResponse> tags = new ArrayList<>();

    public Long getActivityId() {
        return activityId;
        }
        public String getTitle() {
        return title;
        }
        public List<TagResponse> getTags() {
        return tags;
        }
        public void setActivityId(Long activityId) {
        this.activityId = activityId;
        }
        public void setTitle(String title) {
        this.title = title;
        }
        public void setTags(List<TagResponse> tags) {
        this.tags = tags;
        }
        
}
