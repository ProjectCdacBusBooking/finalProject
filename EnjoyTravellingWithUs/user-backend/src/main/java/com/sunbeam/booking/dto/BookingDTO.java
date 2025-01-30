package com.sunbeam.booking.dto;

import com.sunbeam.booking.entity.Booking;
import lombok.Data;

@Data
public class BookingDTO {

    private Long bookingId;
    private Long userId;
    private Long busId;
    private String seatNumber;
    private String bookingDate;
    private String status;

    // DTO Constructor
    public BookingDTO(Booking booking) {
        this.bookingId = booking.getId();
        this.userId = booking.getUser().getId();
        this.busId = booking.getBusId();
        this.seatNumber = booking.getSeatNumber();  // Seat number added
        this.bookingDate = booking.getBookingDate().toString();  // Assuming bookingDate is Date, convert to String
        this.status = booking.getBookingStatus();
    }
}
