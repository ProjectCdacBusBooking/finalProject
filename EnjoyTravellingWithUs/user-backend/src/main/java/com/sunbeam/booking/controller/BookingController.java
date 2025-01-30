package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.BookingConfirmationRequest;
import com.sunbeam.booking.dto.SeatSelectionRequest;
import com.sunbeam.booking.service.BookingService;

import lombok.RequiredArgsConstructor;

/**
 * 📝 BookingController - Booking संबंधित API Handlers
 * 📌 Seat Selection, Booking Confirmation, Booking Management API आहेत.
 */
@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

	@Autowired
    private final BookingService bookingService;

    /**
     * ✅ Select Seats API
     * 📌 वापरकर्ता बस सीट निवडतो.
     * 🟢 URL: POST /api/bookings/select-seats
     */
    @PostMapping("/select-seats")
    public ResponseEntity<?> selectSeats(@RequestBody SeatSelectionRequest request) {
        boolean isSelected = bookingService.selectSeats(request);
        if (isSelected) {
            return ResponseEntity.ok("✅ Seats selected successfully!");
        } else {
            return ResponseEntity.status(400).body("❌ Seats selection failed. Please try again!");
        }
    }
    
    /**
     * ✅ Confirm Booking API
     * 📌 वापरकर्त्याने निवडलेली सीट बुक करून पेमेंट व बुकिंग जनरेशन करते.
     * 🟢 URL: POST /api/bookings/confirm
     */
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmBooking(@RequestBody BookingConfirmationRequest request) {
        boolean isConfirmed = bookingService.confirmBooking(request);
        if (isConfirmed) {
            return ResponseEntity.ok("✅ Booking confirmed successfully!");
        } else {
            return ResponseEntity.status(400).body("❌ Booking confirmation failed. Please try again!");
        }
    }
}
