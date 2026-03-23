package com.epw.activities.entity;
import java.time.Instant;
import java.time.LocalDate;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import jakarta.persistence.CascadeType;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 2000)
    private String description;

    //CONJUNTO DE ACCIONES Y VA A SER DE TIPO STRING VIENE DE ACTIVITYSTATUS.JAVA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ActivityStatus status = ActivityStatus.BACKLOG;

    //CONJUNTO DE ACCIONES Y VA A SER DE TIPO STRING VIENE DE ACTIVITYPRIORITY.JAVA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ActivityPriority priority = ActivityPriority.MEDIUM;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminder> reminders = new ArrayList<>();

    @OneToOne(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private ActivityDetail detail;

    @ManyToMany
    @JoinTable(
    name = "activity_tag",
    joinColumns = @JoinColumn(name = "activity_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    private Instant completedAt;

    //CAMPOS DE AUDITORIA
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;
    //FIN CAMPOS DE AUDITORIA

    @PrePersist
    void onCreate(){
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate(){
        this.updatedAt = Instant.now();
    }

    //Getters/Setters (Si se usa lombock, se puede reemplazar por Getter/@Setter)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
    
    public ActivityPriority getPriority() {
        return priority;
    }
    
    public void setPriority(ActivityPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
    
    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public ActivityDetail getDetail() {
        return detail;
    }

    public void setDetail(ActivityDetail detail) {
        this.detail = detail;
    }
    
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}