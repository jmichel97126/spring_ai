package com.example.springai_gemini_vertex_example001.config;

import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    /**
     * Example of manual configuration for VertexAiGeminiChatModel
     * This is an alternative to auto-configuration
     */
    /*
    @Bean
    public VertexAiGeminiChatModel vertexAiGeminiChatModel() {
        // This would be used if you want manual configuration instead of auto-configuration
        // You would need to provide your own VertexAI instance
        return new VertexAiGeminiChatModel(
            vertexAI, // Your VertexAI instance
            VertexAiGeminiChatOptions.builder()
                .model("gemini-2.0-flash")
                .temperature(0.5)
                .build()
        );
    }
    */
} 