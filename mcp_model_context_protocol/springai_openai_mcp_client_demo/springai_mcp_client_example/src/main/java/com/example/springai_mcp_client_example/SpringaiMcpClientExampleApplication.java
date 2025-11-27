package com.example.springai_mcp_client_example;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringaiMcpClientExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringaiMcpClientExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(List<McpSyncClient> mcpSyncClients, ChatClient.Builder chatClientBuilder) {
        return args -> {
            System.out.println("Spring Ai MCP Client Example Application is running!");
            System.out.println("list of mcpSyncClients: " + mcpSyncClients);
            McpSyncClient mspclient = mcpSyncClients.getFirst();
            System.out.println("mspclient: " + mspclient.listTools());
            SyncMcpToolCallbackProvider toolCallbackProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
            ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();
            // iterate over the toolCallbacks and print them

            for (ToolCallback toolCallback : toolCallbacks) {
                System.out.println("Tool Callback getToolDefinition: " + toolCallback.getToolDefinition());
            }
            var chatClient = chatClientBuilder
                    .defaultToolCallbacks(toolCallbackProvider)
                    .build();
            var userInput = "please give me books name authored by Pritesh mistry";
            System.out.println("\n>>> QUESTION: " + userInput);
            System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
        };
    }

}
