package com.aicoursegenerator.course.controller.mapper;

import com.aicoursegenerator.course.controller.response.CourseGenerationResponse;
import com.aicoursegenerator.course.domain.CourseGeneration;

public class CourseGenerationMapper {

    private CourseGenerationMapper() {
    }

    public static CourseGenerationResponse toResponse(CourseGeneration entity) {
        return new CourseGenerationResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getTopic(),
                entity.getTargetAudience(),
                entity.getDifficulty(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
