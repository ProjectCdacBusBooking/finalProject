package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.entity.Booking;
import com.sunbeam.booking.service.BookingService;

@RestController
@RequestMapping("/api/ticket-bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketBookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<Booking> bookSeat(@RequestParam String seatDetails) {
        // Implement your booking logic here
        Booking booking = new Booking();
        // Assume seatDetails is parsed and booking is created
        bookingService.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
}
