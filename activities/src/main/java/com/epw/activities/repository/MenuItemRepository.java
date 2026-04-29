package com.epw.activities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epw.activities.entity.MenuItem;
import com.epw.activities.entity.Role;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
    List<MenuItem> findByRoleOrderBySortOrderAsc(Role role);
}
