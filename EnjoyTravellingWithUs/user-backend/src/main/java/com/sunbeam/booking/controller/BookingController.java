package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Add a booking
    @PostMapping
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        if (createdBooking != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        if (bookingDTO != null) {
            return ResponseEntity.ok(bookingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cancel booking
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        boolean isCancelled = bookingService.cancelBooking(id);
        if (isCancelled) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Get bookings by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    // Create booking using request params
    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestParam Long userId, @RequestParam Long busId,
            @RequestParam String bookingDate, @RequestParam String seatNumber) {
        BookingDTO bookingDTO = new BookingDTO(null, userId, busId, seatNumber, bookingDate, "CONFIRMED");

        boolean isCreated = bookingService.createBooking(bookingDTO);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Calculate fare
    @GetMapping("/fare/calculate")
    public ResponseEntity<Double> calculateFare(@RequestParam String source, @RequestParam String destination) {
        double fare = bookingService.calculateFare(source, destination);
        return ResponseEntity.ok(fare);
    }
}
