package com.example.mcp_server_sse_webmvc.service;


import com.example.mcp_server_sse_webmvc.model.FlightBooking;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightBookingService {
    private final Map<Long, FlightBooking> bookings = new HashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FlightBookingService() {
        // Initialize with dummy data
        initializeDummyData();
    }

    private void initializeDummyData() {
        {
            // First booking based on the provided example
            FlightBooking booking1 = new FlightBooking(
                    1L,
                    "Delta Air Lines",
                    "JFK",
                    "LAX",
                    LocalDateTime.parse("2024-06-15 08:00", formatter),
                    LocalDateTime.parse("2024-06-15 11:15", formatter),
                    "DL123",
                    "John Doe",
                    "ABC123"
            );

            // Additional dummy bookings
            FlightBooking booking2 = new FlightBooking(
                    2L,
                    "American Airlines",
                    "ORD",
                    "MIA",
                    LocalDateTime.parse("2024-06-20 10:30", formatter),
                    LocalDateTime.parse("2024-06-20 14:45", formatter),
                    "AA456",
                    "Jane Smith",
                    "DEF456"
            );

            FlightBooking booking3 = new FlightBooking(
                    3L,
                    "United Airlines",
                    "SFO",
                    "SEA",
                    LocalDateTime.parse("2024-06-25 14:15", formatter),
                    LocalDateTime.parse("2024-06-25 16:30", formatter),
                    "UA789",
                    "Alice Johnson",
                    "GHI789"
            );

            FlightBooking booking4 = new FlightBooking(
                    4L,
                    "Southwest Airlines",
                    "DEN",
                    "PHX",
                    LocalDateTime.parse("2024-06-30 07:45", formatter),
                    LocalDateTime.parse("2024-06-30 09:00", formatter),
                    "WN101",
                    "Bob Williams",
                    "JKL101"
            );

            // Add all bookings to the map
            bookings.put(booking1.getId(), booking1);
            bookings.put(booking2.getId(), booking2);
            bookings.put(booking3.getId(), booking3);
            bookings.put(booking4.getId(), booking4);
        }
    }

    @Tool(name="get_all_bookings",description = "Get all flight bookings")
    public List<FlightBooking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}
