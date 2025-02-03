package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.BookingConfirmationDTO;

@Service
public class SeatBookingService {

    public BookingConfirmationDTO bookSeat(BookingConfirmationDTO bookingConfirmationDTO) {
        // For now, returning mock confirmation data.
        // In a real application, logic would be added here to save the booking and reserve the seat.
        
        bookingConfirmationDTO.setStatus("Booked");
        bookingConfirmationDTO.setMessage("Your seat has been successfully booked.");
        return bookingConfirmationDTO;
    }
}
