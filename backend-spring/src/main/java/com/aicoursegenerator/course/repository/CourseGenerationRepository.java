package com.aicoursegenerator.course.repository;

import com.aicoursegenerator.course.domain.CourseGeneration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseGenerationRepository extends JpaRepository<CourseGeneration, Long> {
}
