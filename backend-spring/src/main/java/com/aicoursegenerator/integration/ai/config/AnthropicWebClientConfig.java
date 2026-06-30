package com.aicoursegenerator.integration.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AnthropicWebClientConfig {

    @Bean
    public WebClient anthropicWebClient(@Value("${anthropic.api.base-url}") String baseUrl,
                                         @Value("${anthropic.api.key}") String apiKey) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-api-key", apiKey)
                .defaultHeader("anthropic-version", "2023-06-01")
                .defaultHeader("content-type", "application/json")
                .build();
    }
}
