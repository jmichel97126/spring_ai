package com.example.springai_workflow_quickstart.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    ChatClient chatClient;

    public WorkflowService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    String[] workflowSteps = {
            "Summarize the following text in 2-3 sentences.",
            "Extract 3-5 key points from the text as bullet points.",
            "Generate 3 thoughtful follow-up questions about the content."
    };

    public String chain(String userInput) {
        String response = userInput;
        for (String prompt : workflowSteps) {
            String input = String.format("{%s}\n {%s}", prompt, response);

            response = chatClient.prompt(input).call().content();

            // print the response for debugging
            System.out.println("Response: " + response);
        }
        return response;
    }
}
