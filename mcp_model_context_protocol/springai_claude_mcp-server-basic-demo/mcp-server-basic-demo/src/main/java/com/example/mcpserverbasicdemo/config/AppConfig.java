package com.example.mcpserverbasicdemo.config;

import com.example.mcpserverbasicdemo.service.BookDataService;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<ToolCallback> toolCallbacks(BookDataService bookDataService) {
        return List.of(ToolCallbacks.from(bookDataService));
    }
}
