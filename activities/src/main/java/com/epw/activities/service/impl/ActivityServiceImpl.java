package com.epw.activities.service.impl;
import com.epw.activities.dto.ActivityResponse;
import com.epw.activities.dto.CreateActivityRequest;
import com.epw.activities.dto.UpdateActivityRequest;
import com.epw.activities.entity.Activity;
import com.epw.activities.entity.ActivityStatus;
import com.epw.activities.entity.Category;
import com.epw.activities.exception.ResourceNotFoundException;
import com.epw.activities.repository.ActivityRepository;
import com.epw.activities.repository.CategoryRepository;
import com.epw.activities.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository repository;
    private final CategoryRepository categoryRepository;

    public ActivityServiceImpl(ActivityRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ActivityResponse create(CreateActivityRequest request) {
        Activity a = new Activity();
        a.setTitle(request.getTitle());
        a.setDescription(request.getDescription());
        a.setStatus(request.getStatus());
        a.setPriority(request.getPriority());
        a.setDueDate(request.getDueDate());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category " + request.getCategoryId() + " not found"));
            a.setCategory(category);
        }

        Activity saved = repository.save(a);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActivityResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ActivityResponse getById(Long id) {
        Activity a = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Activity " + id + " not found"));
        return toResponse(a);
    }

    @Override
    public ActivityResponse update(Long id, UpdateActivityRequest request) {
        Activity a = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Activity " + id + " not found"));
        a.setTitle(request.getTitle());
        a.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            a.setStatus(request.getStatus());
            if (request.getStatus() != ActivityStatus.DONE) {
                a.setCompletedAt(null);
            }
        }
        if (request.getPriority() != null) {
            a.setPriority(request.getPriority());
        }
        a.setDueDate(request.getDueDate());
        return toResponse(repository.save(a));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Activity " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public ActivityResponse markDone(Long id) {
        Activity a = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Activity " + id + " not found"));
        a.setStatus(ActivityStatus.DONE);
        a.setCompletedAt(Instant.now());
        return toResponse(repository.save(a));
    }

    @Override
    public ActivityResponse changeStatus(Long id, String status) {
        Activity a = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Activity " + id + " not found"));
        ActivityStatus newStatus;
        try {
            newStatus = ActivityStatus.valueOf(status);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        a.setStatus(newStatus);
        if (newStatus == ActivityStatus.DONE) {
            if (a.getCompletedAt() == null) a.setCompletedAt(Instant.now());
        } else {
            a.setCompletedAt(null);
        }
        return toResponse(repository.save(a));
    }

    private ActivityResponse toResponse(Activity a) {
        ActivityResponse r = new ActivityResponse();
        r.setId(a.getId());
        r.setTitle(a.getTitle());
        r.setDescription(a.getDescription());
        r.setStatus(a.getStatus());
        r.setPriority(a.getPriority());
        r.setDueDate(a.getDueDate());
        if (a.getCategory() != null) {
            r.setCategoryId(a.getCategory().getId());
            r.setCategoryName(a.getCategory().getName());
        }
        r.setCompletedAt(a.getCompletedAt());
        r.setCreatedAt(a.getCreatedAt());
        r.setUpdatedAt(a.getUpdatedAt());
        return r;
    }
}
