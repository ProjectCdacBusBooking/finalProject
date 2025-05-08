package com.sunbeam.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.ApiResponse;
import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Keeping React frontend compatibility
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * ✅ **Creates a new booking**
     */
    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        log.info("📌 Creating booking for User ID: {}", bookingDTO.getUserId());
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    /**
     * ✅ **Cancels a booking by ID**
     */
    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<ApiResponse> cancelBooking(@PathVariable Long bookingId) {
        log.info("📌 Cancelling booking with ID: {}", bookingId);
        bookingService.cancelBooking(bookingId); // Throws exception if booking not found
        return ResponseEntity.ok(new ApiResponse("✅ Booking Cancelled Successfully!"));
    }


    /**
     * ✅ **Gets all bookings**
     */
    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        log.info("📌 Fetching all bookings");
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    /**
     * ✅ **Gets a booking by ID**
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        log.info("📌 Fetching booking with ID: {}", bookingId);
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    /**
     * ✅ **Gets all bookings for a user**
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable Long userId) {
        log.info("📌 Fetching bookings for User ID: {}", userId);
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    /**
     * ✅ **Updates a booking by ID**
     */
    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable Long bookingId, @RequestBody BookingDTO bookingDTO) {
        log.info("📌 Updating booking ID: {}", bookingId);
        boolean isUpdated = bookingService.updateBooking(bookingId, bookingDTO);
        if (isUpdated) {
            return ResponseEntity.ok("✅ Booking Updated Successfully!");
        } else {
            throw new ResourceNotFoundException("❌ Booking update failed. ID not found: " + bookingId);
        }
    }
}
