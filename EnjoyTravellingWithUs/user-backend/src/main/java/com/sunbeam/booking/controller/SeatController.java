package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.entity.Seat;
import com.sunbeam.booking.service.SeatService;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/available/{busId}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long busId) {
        List<Seat> availableSeats = seatService.getAvailableSeats(busId);
        return ResponseEntity.ok(availableSeats);
    }

  
}
