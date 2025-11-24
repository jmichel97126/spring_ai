package com.example.springaiopenaifunctioncallingexample;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class AIController {

    private Logger logger = Logger.getLogger(AIController.class.getName());

    private final OpenAiChatModel openAiChatModel;

    @Autowired
    public AIController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/chat")
    public String chat() {
        UserMessage userMessage = new UserMessage("What is the status of Bookings for H001, H002 and H003");
        ChatResponse chatResponse = openAiChatModel.call(new Prompt(List.of(userMessage), OpenAiChatOptions.builder().withFunction("bookingStatus").build()));
        logger.info("chatResponse: " + chatResponse);
        return chatResponse.getResult().getOutput().getContent();
    }
}
