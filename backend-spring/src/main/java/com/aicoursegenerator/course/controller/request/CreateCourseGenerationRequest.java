package com.aicoursegenerator.course.controller.request;

import com.aicoursegenerator.course.domain.CourseDifficulty;

public class CreateCourseGenerationRequest {

    private String title;
    private String topic;
    private String targetAudience;
    private CourseDifficulty difficulty;

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
}
