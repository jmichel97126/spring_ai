package com.example.evaluatoroptimizerexample.controller;

import com.example.evaluatoroptimizerexample.model.RefinedResponse;
import com.example.evaluatoroptimizerexample.service.EvaluatorOptimizer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluatorOptimizerController {
    ChatClient chatClient;

    public EvaluatorOptimizerController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @PostMapping("/process")
    public RefinedResponse processInput(@RequestBody String userInput) {
        RefinedResponse refinedResponse = new EvaluatorOptimizer(chatClient).loop("""
                <user input>
                """ + userInput + """
                </user input>
                """);
        System.out.println("FINAL OUTPUT:\n : " + refinedResponse);
        return refinedResponse;
    }
}
