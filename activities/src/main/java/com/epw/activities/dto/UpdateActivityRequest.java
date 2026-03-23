package com.epw.activities.dto;

import com.epw.activities.entity.ActivityPriority;
import com.epw.activities.entity.ActivityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class UpdateActivityRequest {
    @NotBlank(message = "title is required")
    @Size(max = 120, message = "title must be <= 120 chars")
    private String title;
    @Size(max = 2000, message = "description must be <= 2000 chars")
    private String description;
    private ActivityStatus status;
    private ActivityPriority priority;
    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public ActivityPriority getPriority() {
        return priority;
    }

    public void setPriority(ActivityPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
