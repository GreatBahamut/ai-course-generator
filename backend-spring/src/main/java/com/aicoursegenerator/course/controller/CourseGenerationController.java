package com.aicoursegenerator.course.controller;

import com.aicoursegenerator.course.controller.request.CreateCourseGenerationRequest;
import com.aicoursegenerator.course.domain.CourseGeneration;
import com.aicoursegenerator.course.service.CourseGenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course-generations")
public class CourseGenerationController {

    private final CourseGenerationService courseGenerationService;

    public CourseGenerationController(CourseGenerationService courseGenerationService) {
        this.courseGenerationService = courseGenerationService;
    }

    @PostMapping
    public CourseGeneration create(@RequestBody CreateCourseGenerationRequest request) {
        return courseGenerationService.createCourseGeneration(
                request.getTitle(),
                request.getTopic(),
                request.getTargetAudience(),
                request.getDifficulty()
        );
    }

    @GetMapping
    public List<CourseGeneration> getAll() {
        return courseGenerationService.getAll();
    }

    @GetMapping("/{id}")
    public CourseGeneration getById(@PathVariable Long id) {
        return courseGenerationService.getById(id);
    }

    @PatchMapping("/{id}/start")
    public CourseGeneration start(@PathVariable Long id) {
        return courseGenerationService.startGeneration(id);
    }

    @PatchMapping("/{id}/complete")
    public CourseGeneration complete(@PathVariable Long id) {
        return courseGenerationService.markCompleted(id);
    }

    @PatchMapping("/{id}/fail")
    public CourseGeneration fail(@PathVariable Long id) {
        return courseGenerationService.markFailed(id);
    }
}
