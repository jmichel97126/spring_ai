package com.example.flightbooking_mcp_client.controller;

import com.example.flightbooking_mcp_client.model.FlightResponseMessage;
import com.example.flightbooking_mcp_client.service.FlightBookingClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class FlightBookingController {

    @Autowired
    FlightBookingClientService flightBookingClientService;

    @PostMapping("/chat")
    @CrossOrigin(origins = "http://localhost:3000")
    public FlightResponseMessage chat(@RequestBody String message) {
        System.out.println("request received : " + message);
        FlightResponseMessage response = flightBookingClientService.processInput(message);
        System.out.println("response sent: " + response);
        //convert below to json
        return response;
    }
}