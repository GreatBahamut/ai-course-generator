package com.aicoursegenerator.integration.ai.prompt;

import com.aicoursegenerator.course.domain.CourseGeneration;
import org.springframework.stereotype.Component;

@Component
public class CoursePromptBuilder {

    public String buildPrompt(CourseGeneration courseGeneration) {
        return "Create a course titled \"" + courseGeneration.getTitle() + "\" about \""
                + courseGeneration.getTopic() + "\" for a " + courseGeneration.getTargetAudience()
                + " audience, at a " + courseGeneration.getDifficulty() + " difficulty level.";
    }
}
