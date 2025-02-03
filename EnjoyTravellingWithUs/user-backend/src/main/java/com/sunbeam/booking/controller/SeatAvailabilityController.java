package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.SeatAvailabilityDTO;
import com.sunbeam.booking.service.SeatAvailabilityService;

@RestController
public class SeatAvailabilityController {

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    // API to check seat availability for a specific bus on a specific date
    @GetMapping("/api/seat-availability/{busId}/{date}")
    public SeatAvailabilityDTO checkSeatAvailability(@PathVariable int busId, @PathVariable String date) {
        return seatAvailabilityService.checkSeatAvailability(busId, date);
    }
}
