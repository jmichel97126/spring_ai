package com.example.springaiobservabilitydemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIChatController {

    ChatClient chatClient;

    public AIChatController(ChatClient.Builder chatClientBuilder) {
        chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "tell me one good book to read (just one) for history ?") String message) {
        return chatClient.prompt().user(message).call().chatResponse().getResult().getOutput().getContent();
    }
}
