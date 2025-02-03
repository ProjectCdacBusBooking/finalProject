package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
        boolean isCreated = bookingService.createBooking(bookingDTO);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        if (bookingDTO != null) {
            return ResponseEntity.ok(bookingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        boolean isCancelled = bookingService.cancelBooking(id);
        if (isCancelled) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestParam Long userId, @RequestParam Long busId,
            @RequestParam String bookingDate, @RequestParam String seatNumber) {
        BookingDTO bookingDTO = new BookingDTO(null, userId, busId, bookingDate, seatNumber);
        boolean isCreated = bookingService.createBooking(bookingDTO);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/fare/calculate")
    public ResponseEntity<Double> calculateFare(@RequestParam String source, @RequestParam String destination) {
        double fare = bookingService.calculateFare(source, destination);
        return ResponseEntity.ok(fare);
    }
}
