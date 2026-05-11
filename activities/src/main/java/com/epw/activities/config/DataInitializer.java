package com.epw.activities.config;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epw.activities.entity.MenuItem;
import com.epw.activities.entity.Role;
import com.epw.activities.repository.MenuItemRepository;

@Configuration
public class DataInitializer {
    @Bean
    ApplicationRunner seddMenu(MenuItemRepository repo){
        return args -> {
            if (repo.count() > 0) return;
            repo.saveAll(List.of(
                item("dashboard",       "Dashboard",        Role.ADMIN,  1),
                item("customers",       "Customers",        Role.ADMIN, 2),
                item("departments",     "Departments",      Role.ADMIN, 3),
                item("test-menu-option","Test Menu Option", Role.ADMIN, 4),
                item("about",           "About",            Role.ADMIN, 5),
                item("dashboard",       "Dashboard",        Role.USER,  1),
                item("customers",       "Customers",        Role.USER,  2),
                item("departments",     "Departments",      Role.USER,  3),
                item("about",           "About",            Role.USER,  4)
            ));
        };
    }

    private MenuItem item(String name, String content, Role role, int order){
        MenuItem m = new MenuItem();
        m.setName(name);
        m.setContent(content);
        m.setRole(role);
        m.setOrder(order);
        return m;
    }
}
