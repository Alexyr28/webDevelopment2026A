package com.epw.activities.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Activity> activities = new HashSet<>();

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
