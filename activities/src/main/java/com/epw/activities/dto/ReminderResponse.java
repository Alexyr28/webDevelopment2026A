package com.epw.activities.dto;
import java.time.LocalDateTime;

public class ReminderResponse {
    private Long id;
    private LocalDateTime remindAt;
    private String note;
    private Long activityId;
    
    public Long getId() {return id;}
    public LocalDateTime getRemindAt() {return remindAt;}
    public String getNote() {return note; }
    public Long getActivityId() {return activityId;}
    public void setId(Long id) {this.id = id;}
    public void setRemindAt(LocalDateTime remindAt) {this.remindAt = remindAt; }
    public void setNote(String note) {this.note = note;}
    public void setActivityId(Long activityId) {this.activityId = activityId; }
}
