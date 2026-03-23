package com.epw.activities.controller;
import com.epw.activities.dto.ActivityResponse;
import com.epw.activities.dto.CreateActivityRequest;
import com.epw.activities.dto.UpdateActivityRequest;
import com.epw.activities.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService service;
    public ActivityController(ActivityService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityResponse create(@Valid @RequestBody CreateActivityRequest request) {
        return service.create(request);
    }
    @GetMapping
    public List<ActivityResponse> list() {
        return service.list();
    }
    @GetMapping("/{id}")
    public ActivityResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public ActivityResponse update(@PathVariable Long id, @Valid @RequestBody UpdateActivityRequest request){
        return service.update(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @PatchMapping("/{id}/done")
    public ActivityResponse markDone(@PathVariable Long id) {
        return service.markDone(id);
    }
    @PatchMapping("/{id}/status")
    public ActivityResponse changeStatus(@PathVariable Long id, @RequestParam String value) {
        return service.changeStatus(id, value);
    }
}
