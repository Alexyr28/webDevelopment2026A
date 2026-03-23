package com.epw.activities.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.epw.activities.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
