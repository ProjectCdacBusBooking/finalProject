package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.BookingConfirmationRequest;
import com.sunbeam.booking.dto.BookingDTO;
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
    
    /**
     * ✅ Get Booking History API
     * 📌 वापरकर्त्याच्या सर्व बुकिंग्स (हिस्ट्री) मिळवते.
     * 🟢 URL: GET /api/bookings/history/{userId}
     */
    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getBookingHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingHistory(userId));
    }
    
    /**
     * ✅ Cancel Booking & Refund Process API
     * 📌 बुकिंग रद्द करून वापरकर्त्याला रिफंड प्रोसेस सुरु करते.
     * 🟢 URL: POST /api/bookings/cancel/{bookingId}
     */
    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        boolean cancelStatus = bookingService.cancelBooking(bookingId);
        if (cancelStatus) {
            return ResponseEntity.ok("✅ Booking cancelled successfully. Refund process initiated.");
        } else {
            return ResponseEntity.status(404).body("❌ Booking not found or already cancelled.");
        }
    }
    
    /**
     * ✅ Get Booking Details API
     * 📌 बुकिंग तपशील मिळवण्यासाठी वापरला जातो.
     * 🟢 URL: GET /api/bookings/{bookingId}
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long bookingId) {
        BookingDTO bookingDTO = bookingService.getBookingDetails(bookingId);
        if (bookingDTO != null) {
            return ResponseEntity.ok(bookingDTO);
        } else {
            return ResponseEntity.status(404).body("❌ Booking not found.");
        }
    }
}
