package com.epw.activities.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String name;
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}