package org.example.demo_spring_ai_chatmemory_example;

import org.example.demo_spring_ai_chatmemory_example.service.ChatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class DemoSpringAiChatmemoryExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringAiChatmemoryExampleApplication.class, args);
    }

    @Component
    public static class CommandLineAppStarupRunner implements CommandLineRunner {

        private final ChatService chatService;

        public CommandLineAppStarupRunner(ChatService chatService) {
            this.chatService = chatService;
        }

        @Override
        public void run(String... args) throws Exception {
            chatService.startChat();
        }
    }

}
