package com.epw.activities.service;
import com.epw.activities.dto.MenuItemResponse;
import java.util.List;

public interface MenuService {
    List<MenuItemResponse> getMenuByRole(int roleId);
}
