package com.aicoursegenerator.course.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_generations")
public class CourseGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String topic;

    @Column(name = "target_audience", nullable = false)
    private String targetAudience;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseDifficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseGenerationStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected CourseGeneration() {
        // requerido por JPA
    }

    public static CourseGeneration createPending(String title, String topic, String targetAudience,
                                                   CourseDifficulty difficulty) {
        CourseGeneration courseGeneration = new CourseGeneration();
        courseGeneration.title = title;
        courseGeneration.topic = topic;
        courseGeneration.targetAudience = targetAudience;
        courseGeneration.difficulty = difficulty;
        courseGeneration.status = CourseGenerationStatus.PENDING;
        return courseGeneration;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public CourseDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(CourseDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public CourseGenerationStatus getStatus() {
        return status;
    }

    public void setStatus(CourseGenerationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
