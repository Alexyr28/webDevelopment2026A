package com.epw.activities.service.impl;
import com.epw.activities.dto.MenuItemResponse;
import com.epw.activities.entity.Role;
import com.epw.activities.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    
    private static final List<MenuItemResponse> ADMIN_MENU = List.of(
        new MenuItemResponse("customers", "Customers"),
        new MenuItemResponse("departments", "Departments"),
        new MenuItemResponse("test-menu-option", "Test Menu Option"),
        new MenuItemResponse("about", "About")
    );

    private static final List<MenuItemResponse> USER_MENU = List.of(
        new MenuItemResponse("departments", "Departments"),
        new MenuItemResponse("about", "About")
    );

    @Override
    public List<MenuItemResponse> getMenuByRole(int roleId){
        Role role = Role.values()[roleId];
        return switch(role){
            case ADMIN -> ADMIN_MENU;
            case USER -> USER_MENU;
        };
    }
}
