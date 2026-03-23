package com.epw.activities.service;

import com.epw.activities.dto.ActivityTagResponse;

public interface ActivityTagService {
    ActivityTagResponse assignTag(Long activityId, Long tagId);
    ActivityTagResponse removeTag(Long activityId, Long tagId);
}
