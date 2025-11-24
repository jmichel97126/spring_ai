package com.example.springaigooglevertexgemini001.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    ChatClient chatClient;

    public AiController(ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder.build();
    }

    @GetMapping("/geminichat")
    public String geminiChat(@RequestParam(value = "message", defaultValue = "What is the capital of France ?") String message) {
        return chatClient.prompt().user(message).call().chatResponse().getResult().getOutput().getContent();
    }
}
