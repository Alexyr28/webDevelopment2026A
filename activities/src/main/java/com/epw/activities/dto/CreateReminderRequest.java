package com.epw.activities.dto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class CreateReminderRequest {
    @NotNull(message = "remindAt is required")
    private LocalDateTime remindAt;
    private String note;
    
    public LocalDateTime getRemindAt() {return remindAt; }
    public void setRemindAt(LocalDateTime remindAt) {this.remindAt = remindAt; }
    public String getNote() {return note;}
    public void setNote(String note) {this.note = note;}
}
