package com.example.springai_workflow_mistral_parallel.controller;


import com.example.springai_workflow_mistral_parallel.service.ParallelworkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParallelWorlflowController {

    @Autowired
    private ParallelworkflowService parallelworkflowService;

    String coreMessage = "Introducing our innovative new eco-friendly product that will revolutionize your daily routine!";

    List<String> platformInstructions = List.of(
            """
            Generate a tweet for Twitter based on the core message.
            Keep it concise (under 280 characters), engaging, and include relevant hashtags.
            """,
            """
            Create a Facebook post based on the core message.
            It can be a bit longer, more descriptive, and encourage user interaction (e.g., ask a question).
            """,
            """
            Write an Instagram caption based on the core message.
            Focus on visual appeal and use relevant hashtags. Keep it relatively short and impactful.
            """,
            """
            Develop a LinkedIn post based on the core message.
            Maintain a professional tone, highlight the benefits for professionals, and consider including a call to action to learn more.
            """
    );

    @GetMapping("/parallel2")
    public  List<String>  parallelworkflow() {
        String promptTemplate = """
            Adapt the following core marketing message for the specified social media platform,
            following the specific instructions provided.
        
            Core Message:
            %s
        
            Instructions for the platform:
            %s
            """;

        String prompt = String.format(promptTemplate, coreMessage);

        // Call the parallel service to get the result
        List<String> parallelResponses = parallelworkflowService
                .parallel(promptTemplate,
                        platformInstructions,
                        3); // Number of parallel executions (adjust as needed)

        System.out.println("\nGenerated Social Media Posts:");
        for (int i = 0; i < parallelResponses.size(); i++) {
            System.out.println("Platform " + (i + 1) + ":\n" + parallelResponses.get(i) + "\n---");
        }
        return parallelResponses;
    }
}
