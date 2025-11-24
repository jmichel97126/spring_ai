package com.example.springai_tool_calling_demo;

import com.example.springai_tool_calling_demo.tools.DevOpsTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringaiToolCallingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringaiToolCallingDemoApplication.class, args);
    }

    @Bean
    public org.springframework.boot.CommandLineRunner commandLineRunner(ChatClient.Builder builder) {
        return args -> {
            System.out.println("CommandLineRunner executed at startup.");
            var chat = builder.build();
            var scanner = new Scanner(System.in);

            MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                    .maxMessages(10)
                    .build();

            System.out.println("\nLet's chat!");
            while (true) {
                System.out.print("\nUSER: ");
                System.out.println("ASSISTANT: " +
                        chat.prompt(scanner.nextLine())
                                .advisors(
                                        MessageChatMemoryAdvisor.builder(chatMemory).build())
                                .tools(new DevOpsTools())
                                .call().
                                content());
            }
        };
    }

    @Bean
    public List<ToolCallback> toolCallbacks(DevOpsTools devOpsTools) {
        return List.of(ToolCallbacks.from(devOpsTools));
    }


}