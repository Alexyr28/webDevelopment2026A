package com.epw.activities.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
@Table(name = "reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime remindAt;

    @Column(length = 255)
    private String note;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    public Long getId() {
        return id;
    }
    
    public LocalDateTime getRemindAt() {
        return remindAt;
    }

    public String getNote() {
        return note;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRemindAt(LocalDateTime remindAt) {
        this.remindAt = remindAt;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
