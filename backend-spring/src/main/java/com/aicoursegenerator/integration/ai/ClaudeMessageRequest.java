package com.aicoursegenerator.integration.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

record ClaudeMessageRequest(String model, @JsonProperty("max_tokens") int maxTokens, List<ClaudeMessage> messages) {

    record ClaudeMessage(String role, String content) {
    }
}
