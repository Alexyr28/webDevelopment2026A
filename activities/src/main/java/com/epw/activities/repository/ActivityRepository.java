package com.epw.activities.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.epw.activities.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    
}