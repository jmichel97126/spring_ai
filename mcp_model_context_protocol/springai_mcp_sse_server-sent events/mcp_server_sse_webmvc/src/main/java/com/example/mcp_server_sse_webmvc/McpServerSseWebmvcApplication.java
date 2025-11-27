package com.example.mcp_server_sse_webmvc;

import com.example.mcp_server_sse_webmvc.service.HotelBookingService;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@RestController
public class McpServerSseWebmvcApplication {

    McpSyncServer mcpSyncServer ;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(McpServerSseWebmvcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(McpServerSseWebmvcApplication.class, args);
    }

    // constructor
    public McpServerSseWebmvcApplication(McpSyncServer mcpSyncServer) {
        this.mcpSyncServer = mcpSyncServer;
    }


    @GetMapping("/updateTools")
    public String greeting() {
        System.out.println("Update tools signal received!");
        List<McpServerFeatures.SyncToolSpecification> newTools = McpToolUtils
                .toSyncToolSpecifications(ToolCallbacks.from(new HotelBookingService()));

        for (McpServerFeatures.SyncToolSpecification newTool : newTools) {
            logger.info("Add new tool: " + newTool);
            mcpSyncServer.addTool(newTool);
        }
        return "Update signal received!";
    }
}
