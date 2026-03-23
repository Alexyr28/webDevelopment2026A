package com.epw.activities.service.impl;

import com.epw.activities.dto.ActivityTagResponse;
import com.epw.activities.dto.TagResponse;
import com.epw.activities.entity.Activity;
import com.epw.activities.entity.Tag;
import com.epw.activities.exception.ResourceNotFoundException;
import com.epw.activities.repository.ActivityRepository;
import com.epw.activities.repository.TagRepository;
import com.epw.activities.service.ActivityTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ActivityTagServiceImpl implements ActivityTagService{
    private final ActivityRepository activityRepository;
    private final TagRepository tagRepository;

    public ActivityTagServiceImpl(ActivityRepository activityRepository, TagRepository tagRepository) {
        this.activityRepository = activityRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ActivityTagResponse assignTag(Long activityId, Long tagId) {
        Activity activity = activityRepository.findById(activityId)
        .orElseThrow(() -> new ResourceNotFoundException("Activity " + activityId + " not found"));
        Tag tag = tagRepository.findById(tagId)
        .orElseThrow(() -> new ResourceNotFoundException("Tag " + tagId + " not found"));
        activity.getTags().add(tag);
        Activity saved = activityRepository.save(activity);
        return toResponse(saved);
    }

    @Override
    public ActivityTagResponse removeTag(Long activityId, Long tagId) {
        Activity activity = activityRepository.findById(activityId)
        .orElseThrow(() -> new ResourceNotFoundException("Activity " + activityId + " not found"));
        Tag tag = tagRepository.findById(tagId)
        .orElseThrow(() -> new ResourceNotFoundException("Tag " + tagId + " not found"));
        activity.getTags().remove(tag);
        Activity saved = activityRepository.save(activity);
        return toResponse(saved);
    }

    private ActivityTagResponse toResponse(Activity activity) {
        ActivityTagResponse response = new ActivityTagResponse();
        response.setActivityId(activity.getId());
        response.setTitle(activity.getTitle());
        List<TagResponse> tags = activity.getTags().stream().map(tag -> {
        TagResponse tr = new TagResponse();
        tr.setId(tag.getId());
        tr.setName(tag.getName());
        return tr;
        }).toList();
        response.setTags(tags);
        return response;
    }
}
