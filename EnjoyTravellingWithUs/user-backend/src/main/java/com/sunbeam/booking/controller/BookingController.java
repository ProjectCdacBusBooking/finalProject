package com.sunbeam.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.booking.dto.ApiResponse;
import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.service.BookingService;

@RestController // Haa class REST API controller ahe
@RequestMapping("/api/bookings") // Saglya booking-related requests sathi base path define karto
@CrossOrigin(origins = "http://localhost:3000") // React frontend sathi CORS enable karto
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class); // Logging sathi logger declare karto
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * ✅ **Navi booking create karto**
     */
    @PostMapping("/create") // HTTP POST request handle karto
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        log.info("📌 Creating booking for User ID: {}", bookingDTO.getUserId()); // Log madhe user ID print karto
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO); // Service madhe booking create karto
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking); // 201 CREATED status return karto
    }

    /**
     * ✅ **Booking cancel karto ID varun**
     */
    @DeleteMapping("/cancel/{bookingId}") // HTTP DELETE request handle karto
    public ResponseEntity<ApiResponse> cancelBooking(@PathVariable Long bookingId) {
        log.info("📌 Cancelling booking with ID: {}", bookingId); // Log madhe booking ID print karto
        bookingService.cancelBooking(bookingId); // Booking cancel karto (Exception asel tar throw hote)
        return ResponseEntity.ok(new ApiResponse("✅ Booking Cancelled Successfully!")); // Success response return karto
    }

    /**
     * ✅ **Saglya bookings fetch karto**
     */
    @GetMapping("/all") // HTTP GET request handle karto
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        log.info("📌 Fetching all bookings"); // Log madhe message print karto
        return ResponseEntity.ok(bookingService.getAllBookings()); // Booking list return karto
    }

    /**
     * ✅ **Ek specific booking gheto ID varun**
     */
    @GetMapping("/{bookingId}") // HTTP GET request handle karto
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        log.info("📌 Fetching booking with ID: {}", bookingId); // Log madhe booking ID print karto
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId); // ID varun booking fetch karto
        return ResponseEntity.ok(bookingDTO); // Response return karto
    }

    /**
     * ✅ **Ekha user sathi saglya bookings fetch karto**
     */
    @GetMapping("/user/{userId}") // HTTP GET request handle karto
    public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable Long userId) {
        log.info("📌 Fetching bookings for User ID: {}", userId); // Log madhe user ID print karto
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId)); // User specific bookings return karto
    }

    /**
     * ✅ **Booking update karto ID varun**
     */
    @PutMapping("/update/{bookingId}") // HTTP PUT request handle karto
    public ResponseEntity<String> updateBooking(@PathVariable Long bookingId, @RequestBody BookingDTO bookingDTO) {
        log.info("📌 Updating booking ID: {}", bookingId); // Log madhe booking ID print karto
        boolean isUpdated = bookingService.updateBooking(bookingId, bookingDTO); // Booking update honar ka te check karto
        if (isUpdated) {
            return ResponseEntity.ok("✅ Booking Updated Successfully!"); // Success response return karto
        } else {
            throw new ResourceNotFoundException("❌ Booking update failed. ID not found: " + bookingId); // Exception throw karto
        }
    }
}
