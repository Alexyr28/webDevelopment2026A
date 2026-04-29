package com.epw.activities.service.impl;
import com.epw.activities.dto.MenuItemResponse;
import com.epw.activities.entity.Role;
import com.epw.activities.repository.MenuItemRepository;
import com.epw.activities.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    
    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItemResponse> getMenuByRole(String roleName){
        Role role = Role.valueOf(roleName.toUpperCase());
        return menuItemRepository.findByRoleOrderBySortOrderAsc(role).stream().map(item -> new MenuItemResponse(item.getName(), item.getContent())).toList();
    }
}
