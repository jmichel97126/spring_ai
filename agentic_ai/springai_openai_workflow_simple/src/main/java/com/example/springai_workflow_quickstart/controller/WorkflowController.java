package com.example.springai_workflow_quickstart.controller;

import com.example.springai_workflow_quickstart.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;


    @PostMapping("/workflow")
    public String processWorkflow(@RequestParam  String userInput) {
        // Delegate the workflow processing to the service
        return workflowService.chain(userInput);
    }
}
