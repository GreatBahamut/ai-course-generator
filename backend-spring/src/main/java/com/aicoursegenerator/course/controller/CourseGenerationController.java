package com.aicoursegenerator.course.controller;

import com.aicoursegenerator.course.controller.mapper.CourseGenerationMapper;
import com.aicoursegenerator.course.controller.request.CreateCourseGenerationRequest;
import com.aicoursegenerator.course.controller.response.CourseGenerationResponse;
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
    public CourseGenerationResponse create(@RequestBody CreateCourseGenerationRequest request) {
        var created = courseGenerationService.createCourseGeneration(
                request.getTitle(),
                request.getTopic(),
                request.getTargetAudience(),
                request.getDifficulty()
        );
        return CourseGenerationMapper.toResponse(created);
    }

    @GetMapping
    public List<CourseGenerationResponse> getAll() {
        return courseGenerationService.getAll().stream()
                .map(CourseGenerationMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CourseGenerationResponse getById(@PathVariable Long id) {
        var courseGeneration = courseGenerationService.getById(id);
        return CourseGenerationMapper.toResponse(courseGeneration);
    }

    @PatchMapping("/{id}/start")
    public CourseGenerationResponse start(@PathVariable Long id) {
        var courseGeneration = courseGenerationService.startGeneration(id);
        return CourseGenerationMapper.toResponse(courseGeneration);
    }

    @PatchMapping("/{id}/complete")
    public CourseGenerationResponse complete(@PathVariable Long id) {
        var courseGeneration = courseGenerationService.markCompleted(id);
        return CourseGenerationMapper.toResponse(courseGeneration);
    }

    @PatchMapping("/{id}/fail")
    public CourseGenerationResponse fail(@PathVariable Long id) {
        var courseGeneration = courseGenerationService.markFailed(id);
        return CourseGenerationMapper.toResponse(courseGeneration);
    }
}
