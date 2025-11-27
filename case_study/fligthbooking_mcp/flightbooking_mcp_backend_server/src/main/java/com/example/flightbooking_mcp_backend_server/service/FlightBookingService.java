package com.example.flightbooking_mcp_backend_server.service;

import com.example.flightbooking_mcp_backend_server.model.FlightBooking;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FlightBookingService {

    private final Map<Long, FlightBooking> bookings = new HashMap<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FlightBookingService() {
        // Initialize with dummy data
        initializeDummyData();
    }

    private void initializeDummyData() {
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

    @Tool(name="get_all_bookings",description = "Get all flight bookings")
    public List<FlightBooking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }

    @Tool(name="get_booking_by_id",description = "Get flight booking by ID")
    public List<FlightBooking> getBookingById(Long id) {
        List<FlightBooking> result = new ArrayList<>();
        if (bookings.containsKey(id)) {
            result.add(bookings.get(id));
        }
        return result;
    }

    @Tool(name="get_booking_by_reference",description = "Get flight booking by booking reference")
    public List<FlightBooking> getBookingByReference(String bookingRef) {
        List<FlightBooking> result = new ArrayList<>();
        for (FlightBooking booking : bookings.values()) {
            if (booking.getBookingRef().equalsIgnoreCase(bookingRef)) {
                result.add(booking);
            }
        }
        return result;
    }

    @Tool(name="create_booking",description = "Create a new flight booking")
    public List<FlightBooking> createBooking(FlightBooking booking) {
        Long newId = bookings.keySet().stream()
                .max(Long::compareTo)
                .orElse(0L) + 1;
        booking.setId(newId);
        bookings.put(newId, booking);
        return new ArrayList<>(bookings.values());
    }

    @Tool(name="update_booking",description = "Update an existing flight booking")
    public List<FlightBooking> updateBooking(Long id, FlightBooking updatedBooking) {
        if (bookings.containsKey(id)) {
            updatedBooking.setId(id);
            bookings.put(id, updatedBooking);
            return new ArrayList<>(bookings.values());
        }
        return Collections.emptyList();
    }

    @Tool(name="delete_booking",description = "Delete a flight booking by ID")
    public List<FlightBooking> deleteBooking(Long id) {
        if (bookings.containsKey(id)) {
            bookings.remove(id);
            return new ArrayList<>(bookings.values());
        }
        return Collections.emptyList();
    }

    @Tool(name="delete_booking_by_reference",description = "Delete a flight booking by booking reference")
    public  List<FlightBooking> deleteBookingByReference(String bookingRef) {
        List<FlightBooking> result = new ArrayList<>();
        for (Iterator<Map.Entry<Long, FlightBooking>> it = bookings.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Long, FlightBooking> entry = it.next();
            if (entry.getValue().getBookingRef().equalsIgnoreCase(bookingRef)) {
                it.remove();
                result.add(entry.getValue());
            }
        }
        return result;
    }

}
