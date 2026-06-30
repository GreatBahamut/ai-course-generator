package com.aicoursegenerator.course.service;

import com.aicoursegenerator.course.domain.CourseDifficulty;
import com.aicoursegenerator.course.domain.CourseGeneration;
import com.aicoursegenerator.course.domain.CourseGenerationStatus;
import com.aicoursegenerator.course.exception.CourseGenerationNotFoundException;
import com.aicoursegenerator.course.repository.CourseGenerationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGenerationService {

    private final CourseGenerationRepository courseGenerationRepository;

    public CourseGenerationService(CourseGenerationRepository courseGenerationRepository) {
        this.courseGenerationRepository = courseGenerationRepository;
    }

    public CourseGeneration createCourseGeneration(String title, String topic, String targetAudience,
                                                     CourseDifficulty difficulty) {
        CourseGeneration courseGeneration = CourseGeneration.createPending(title, topic, targetAudience, difficulty);
        return courseGenerationRepository.save(courseGeneration);
    }

    public CourseGeneration getById(Long id) {
        return getOrThrow(id);
    }

    public List<CourseGeneration> getAll() {
        return courseGenerationRepository.findAll();
    }

    public CourseGeneration startGeneration(Long id) {
        CourseGeneration courseGeneration = getOrThrow(id);

        if (courseGeneration.getStatus() != CourseGenerationStatus.PENDING) {
            throw new IllegalStateException(
                    "CourseGeneration must be in PENDING status to start generation. Current status: "
                            + courseGeneration.getStatus());
        }

        courseGeneration.setStatus(CourseGenerationStatus.GENERATING);
        return courseGenerationRepository.save(courseGeneration);
    }

    public CourseGeneration markCompleted(Long id) {
        CourseGeneration courseGeneration = getOrThrow(id);

        if (courseGeneration.getStatus() != CourseGenerationStatus.GENERATING) {
            throw new IllegalStateException(
                    "CourseGeneration must be in GENERATING status to mark as completed. Current status: "
                            + courseGeneration.getStatus());
        }

        courseGeneration.setStatus(CourseGenerationStatus.COMPLETED);
        return courseGenerationRepository.save(courseGeneration);
    }

    public CourseGeneration markFailed(Long id) {
        CourseGeneration courseGeneration = getOrThrow(id);

        if (courseGeneration.getStatus() != CourseGenerationStatus.GENERATING) {
            throw new IllegalStateException(
                    "CourseGeneration must be in GENERATING status to mark as failed. Current status: "
                            + courseGeneration.getStatus());
        }

        courseGeneration.setStatus(CourseGenerationStatus.FAILED);
        return courseGenerationRepository.save(courseGeneration);
    }

    private CourseGeneration getOrThrow(Long id) {
        return courseGenerationRepository.findById(id)
                .orElseThrow(() -> new CourseGenerationNotFoundException(id));
    }
}
