package com.example.flightbooking_mcp_client.model;

import java.time.LocalDateTime;

public class FlightBooking {
    private Long id;
    private String airline;
    private String from;
    private String to;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String flightNumber;
    private String passenger;
    private String bookingRef;

    // Constructors
    public FlightBooking() {
    }

    public FlightBooking(Long id, String airline, String from, String to, LocalDateTime departure,
                         LocalDateTime arrival, String flightNumber, String passenger, String bookingRef) {
        this.id = id;
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.arrival = arrival;
        this.flightNumber = flightNumber;
        this.passenger = passenger;
        this.bookingRef = bookingRef;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    @Override
    public String toString() {
        return "FlightBooking{" +
                "id=" + id +
                ", airline='" + airline + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", flightNumber='" + flightNumber + '\'' +
                ", passenger='" + passenger + '\'' +
                ", bookingRef='" + bookingRef + '\'' +
                '}';
    }
}
