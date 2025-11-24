package com.example.springai_tool_calling_demo.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class DevOpsTools {

    @Tool(description = "Get the current deployment status of a microservice in a specific environment")
    public String getDeploymentStatus(@ToolParam( description = "Name of the microservice or service") String serviceName,
                                      @ToolParam( description = "Environment (e.g., production, staging)") String environment) {
        System.out.println("getDeploymentStatus called with input: " + serviceName + ", " + environment);
        return "Deployment status for service '" + serviceName + "' in environment '" + environment + "': " +
                "Service is down with 3 our of 5 pods,";
    }

    @Tool(description = "Trigger a rolling restart of a microservice in a given environment")
    public String restartService(@ToolParam (description = "Name of the microservice or service") String serviceName,
                                 @ToolParam(description = "Environment (e.g., production, staging)") String environment) {
        System.out.println("restartService called with input: " + serviceName + ", " + environment);
        return "Rolling restart of service '" + serviceName + "' in environment '" + environment + "' initiated. " +
                "All pods will be restarted sequentially to ensure zero downtime.";
    }
}