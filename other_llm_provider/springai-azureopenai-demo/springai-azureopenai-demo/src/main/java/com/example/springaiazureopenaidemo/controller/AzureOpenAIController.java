package com.example.springaiazureopenaidemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AzureOpenAIController {
    ChatClient chatClient;

    public AzureOpenAIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/azurechat")
    public String chat(@RequestParam(value="message", defaultValue="Give me a top five cities in United states") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse().getResult().getOutput().getContent();
    }
}
