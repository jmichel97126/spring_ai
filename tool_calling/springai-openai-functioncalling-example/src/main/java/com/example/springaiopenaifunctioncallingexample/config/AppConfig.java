package com.example.springaiopenaifunctioncallingexample.config;

import com.example.springaiopenaifunctioncallingexample.service.MockBookingStatusService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class AppConfig {
    @Bean
    @Description("Get the status of a hotel booking")
    public Function<MockBookingStatusService.BookingRequest,MockBookingStatusService.BookingResponse> bookingStatus(){
        return new MockBookingStatusService();
    }
}
