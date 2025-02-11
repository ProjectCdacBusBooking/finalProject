package com.sunbeam.booking.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sunbeam.booking.entity.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {
    private Long id;
    private String name;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String busNumber;
    private String route;
    private int capacity;
    private int totalSeats;
    private int availableSeats;
    private double fare;

    // ✅ Ensure consistent DateTime parsing
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public BusDTO(Long id, String name, String source, String destination, String departureTimeStr, String arrivalTimeStr, 
                  String busNumber, String route, int capacity, int totalSeats, int availableSeats, double fare) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.departureTime = parseDateTime(departureTimeStr);
        this.arrivalTime = parseDateTime(arrivalTimeStr);
        this.busNumber = busNumber;
        this.route = route;
        this.capacity = capacity;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }
    
    public BusDTO(Bus bus) {
        this.id = bus.getId();
        this.name = bus.getName();
        this.source = bus.getSource();
        this.destination = bus.getDestination();
        this.departureTime = bus.getDepartureTime();
        this.arrivalTime = bus.getArrivalTime();
        this.busNumber = bus.getBusNumber();
        this.route = bus.getRoute();
        this.capacity = bus.getCapacity();
        this.totalSeats = bus.getTotalSeats();
        this.availableSeats = bus.getAvailableSeats();
        this.fare = bus.getFare();
    }



    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr.length() == 16) { // If missing seconds, append ":00"
            dateTimeStr += ":00";
        }
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }
}
