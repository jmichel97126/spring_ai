package com.example.springai_orchestratorworkers_example.controller;


import com.example.springai_orchestratorworkers_example.service.OrchestratorWorkers;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrchestratorWorkersCotroller {



    ChatClient chatClient;

    public OrchestratorWorkersCotroller(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/process")
    public OrchestratorWorkers.FinalResponse processTask(@RequestBody String taskDescription) {
        return new OrchestratorWorkers(chatClient).process(taskDescription);
    }


}
