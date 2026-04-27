package com.epw.activities.controller;
import com.epw.activities.dto.MenuItemResponse;
import com.epw.activities.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/{id_rol}")
    public List<MenuItemResponse> getMenu(@PathVariable("id_rol") int roleId){
        return menuService.getMenuByRole(roleId);
    }
}
