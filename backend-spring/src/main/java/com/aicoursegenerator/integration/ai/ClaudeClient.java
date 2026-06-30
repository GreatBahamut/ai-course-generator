package com.aicoursegenerator.integration.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ClaudeClient implements AIClient {

    private final WebClient anthropicWebClient;
    private final String model;
    private final int maxTokens;

    public ClaudeClient(WebClient anthropicWebClient,
                         @Value("${anthropic.api.model}") String model,
                         @Value("${anthropic.api.max-tokens}") int maxTokens) {
        this.anthropicWebClient = anthropicWebClient;
        this.model = model;
        this.maxTokens = maxTokens;
    }

    @Override
    public String generateContent(String prompt) {
        ClaudeMessageRequest request = new ClaudeMessageRequest(
                model,
                maxTokens,
                List.of(new ClaudeMessageRequest.ClaudeMessage("user", prompt))
        );

        ClaudeMessageResponse response = anthropicWebClient.post()
                .uri("/v1/messages")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ClaudeMessageResponse.class)
                .block();

        return response.content().get(0).text();
    }
}
