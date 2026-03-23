package com.epw.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epw.activities.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {}
