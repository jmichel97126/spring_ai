package com.example.springai_gemini_vertex_example001.controller;


import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class ChatController {

    private final VertexAiGeminiChatModel chatModel;

    @Autowired
    public ChatController(VertexAiGeminiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * Simple text generation endpoint
     */
    @GetMapping("/generate")
    public Map<String, String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var response = this.chatModel.call(message);
        return Map.of("generation", response);
    }

    /**
     * Streaming text generation endpoint
     */
    @GetMapping("/generateStream")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(message);
        return this.chatModel.stream(prompt).map(response -> response.toString());
    }

    /**
     * Chat with custom temperature
     */
    @PostMapping("/chat")
    public Map<String, Object> chatWithOptions(@RequestBody ChatRequest request) {
        var response = this.chatModel.call(
            new Prompt(
                request.getMessage(),
                VertexAiGeminiChatOptions.builder()
                    .temperature(request.getTemperature() != null ? request.getTemperature() : 0.5)
                    .build()
            )
        );
        
        return Map.of(
                "message", request.getMessage(),
                "response", response,
                "temperature", String.valueOf(request.getTemperature() != null ? request.getTemperature() : 0.5)
        );
    }

    /**
     * Template-based generation
     */
    @GetMapping("/template")
    public Map<String, String> generateWithTemplate(@RequestParam String topic) {
        PromptTemplate template = new PromptTemplate("Write a short story about {topic}");
        Prompt prompt = template.create(Map.of("topic", topic));
        var response = this.chatModel.call(prompt);
        
        return Map.of(
                "topic", topic,
                "story", response.toString()
        );
    }

    /**
     * Request class for chat endpoint
     */
    public static class ChatRequest {
        private String message;
        private Double temperature;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Double getTemperature() {
            return temperature;
        }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }
    }
} 