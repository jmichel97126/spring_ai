package com.example.springaimistralaidemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {
    ChatClient chatClient;

    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message", defaultValue="List of famous french painters ?") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse().getResult().getOutput().getContent();
    }
}
