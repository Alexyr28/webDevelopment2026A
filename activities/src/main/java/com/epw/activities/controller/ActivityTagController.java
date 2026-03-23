package com.epw.activities.controller;

import com.epw.activities.dto.ActivityTagResponse;
import com.epw.activities.service.ActivityTagService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/activities/{activityId}/tags")
public class ActivityTagController {
    private final ActivityTagService activityTagService;
    public ActivityTagController(ActivityTagService activityTagService) {
        this.activityTagService = activityTagService;
    }

    @PostMapping("/{tagId}")
        public ActivityTagResponse assignTag(@PathVariable Long activityId, @PathVariable Long tagId) {
        return activityTagService.assignTag(activityId, tagId);
    }

    @DeleteMapping("/{tagId}")
        public ActivityTagResponse removeTag(@PathVariable Long activityId, @PathVariable Long tagId) {
        return activityTagService.removeTag(activityId, tagId);
    }
}
