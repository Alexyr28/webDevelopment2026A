package com.epw.activities.service;
import com.epw.activities.dto.ActivityResponse;
import com.epw.activities.dto.CreateActivityRequest;
import com.epw.activities.dto.UpdateActivityRequest;
import java.util.List;

public interface ActivityService {
    ActivityResponse create(CreateActivityRequest request);
    List<ActivityResponse> list();
    ActivityResponse getById(Long id);
    ActivityResponse update(Long id, UpdateActivityRequest request);
    void delete(Long id);

    ActivityResponse markDone(Long id);
    ActivityResponse changeStatus(Long id, String status);
}
