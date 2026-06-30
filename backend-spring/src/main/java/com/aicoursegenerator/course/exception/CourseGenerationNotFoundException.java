package com.aicoursegenerator.course.exception;

public class CourseGenerationNotFoundException extends RuntimeException {

    public CourseGenerationNotFoundException(Long id) {
        super("CourseGeneration not found with id: " + id);
    }
}
