package com.epw.activities.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "activity_detail")
public class ActivityDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String place;
    private Integer estimatedMinutes;

    @Column(length = 1000)
    private String privateNotes;

    @OneToOne
    @JoinColumn(name = "activity_id", unique = true, nullable = false)
    private Activity activity;

    public Long getId() {
        return id;
    }
    
    public String getPlace() {
        return place;
    }

    public Integer getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public String getPrivateNotes() {
        return privateNotes;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setEstimatedMinutes(Integer estimatedMinutes) {
        this.estimatedMinutes = estimatedMinutes;
    }

    public void setPrivateNotes(String privateNotes) {
        this.privateNotes = privateNotes;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
