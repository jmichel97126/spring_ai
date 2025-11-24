package com.example.springai_tool_programatically.tools;

import org.springframework.stereotype.Component;

/**
 * DevOps tools for simulated microservice management operations.
 * Converted from @Tool annotations to plain methods for programmatic tool registration.
 */
@Component
public class DevOpsTools {

    private String currentVersion = "1.4.2";
    private final int podCount = 5;

    /**
     * Get the current deployment status of a microservice
     * Previously: @Tool(description = "Retrieves the current deployment status of a microservice")
     */
    public String getDeploymentStatus(String serviceName, String environment) {
        System.out.println("🔍 Tool executed: getDeploymentStatus()");

        boolean isHealthy = false;
        String healthStatus = "unhealthy";

        return String.format(
                "Service '%s' in environment '%s' is currently running version %s with %d pods %s.",
                serviceName, environment, currentVersion, podCount, healthStatus
        );
    }

    /**
     * Triggers a rolling restart of a microservice
     * Previously: @Tool(description = "Triggers a rolling restart of a microservice")
     */
    public String restartService(String serviceName, String environment) {
        System.out.println("🔄 Tool executed: restartService()");

        // Simulate version increment
        String[] versionParts = currentVersion.split("\\.");
        int patch = Integer.parseInt(versionParts[2]) + 1;
        currentVersion = versionParts[0] + "." + versionParts[1] + "." + patch;

        return String.format(
                "Rolling restart initiated for service '%s' in environment '%s' with %d pods. " +
                        "Expected downtime is less than 1 minute. " +
                        "Service updated to version %s.",
                serviceName, environment, podCount, currentVersion
        );
    }
}