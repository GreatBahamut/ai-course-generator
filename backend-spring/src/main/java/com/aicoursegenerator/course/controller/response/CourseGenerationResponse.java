package com.aicoursegenerator.course.controller.response;

import com.aicoursegenerator.course.domain.CourseDifficulty;
import com.aicoursegenerator.course.domain.CourseGenerationStatus;

import java.time.LocalDateTime;

public class CourseGenerationResponse {

    private final Long id;
    private final String title;
    private final String topic;
    private final String targetAudience;
    private final CourseDifficulty difficulty;
    private final CourseGenerationStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CourseGenerationResponse(Long id, String title, String topic, String targetAudience,
                                     CourseDifficulty difficulty, CourseGenerationStatus status,
                                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.targetAudience = targetAudience;
        this.difficulty = difficulty;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public CourseDifficulty getDifficulty() {
        return difficulty;
    }

    public CourseGenerationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
