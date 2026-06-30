package com.aicoursegenerator.automation.controller;

import com.aicoursegenerator.course.controller.mapper.CourseGenerationMapper;
import com.aicoursegenerator.course.controller.request.CompleteCourseGenerationRequest;
import com.aicoursegenerator.course.controller.response.CourseGenerationResponse;
import com.aicoursegenerator.course.service.CourseGenerationService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/automation/course-generations")
public class AutomationController {

    private final CourseGenerationService courseGenerationService;

    public AutomationController(CourseGenerationService courseGenerationService) {
        this.courseGenerationService = courseGenerationService;
    }

    @PostMapping("/{id}/start")
    public CourseGenerationResponse start(@PathVariable Long id) {
        var courseGeneration = courseGenerationService.startGeneration(id);
        return CourseGenerationMapper.toResponse(courseGeneration);
    }

    @PatchMapping("/{id}/result")
    public CourseGenerationResponse result(@PathVariable Long id,
                                            @RequestBody CompleteCourseGenerationRequest request) {
        var courseGeneration = courseGenerationService.completeGeneration(id, request.getGeneratedContent());
        return CourseGenerationMapper.toResponse(courseGeneration);
    }
}
