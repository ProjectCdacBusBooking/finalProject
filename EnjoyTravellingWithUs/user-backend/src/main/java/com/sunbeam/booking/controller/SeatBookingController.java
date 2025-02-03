package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.BookingConfirmationDTO;
import com.sunbeam.booking.service.SeatBookingService;

@RestController
public class SeatBookingController {

    @Autowired
    private SeatBookingService seatBookingService;

    // API to confirm the seat booking
    @PostMapping("/api/book-seat")
    public BookingConfirmationDTO bookSeat(@RequestBody BookingConfirmationDTO bookingConfirmationDTO) {
        return seatBookingService.bookSeat(bookingConfirmationDTO);
    }
}
