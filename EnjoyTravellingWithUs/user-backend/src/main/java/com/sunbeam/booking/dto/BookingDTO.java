package com.sunbeam.booking.dto;

import java.util.Date;

import com.sunbeam.booking.entity.Booking;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {
    private Long bookingId;
    private Long userId;
    private Long busId;
    private String seatNumber;
    private Date bookingDate;
    private String status;

    public BookingDTO(Long bookingId, Long userId, Long busId, String seatNumber, Date bookingDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.busId = busId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public BookingDTO(Booking booking) {
        this.bookingId = booking.getId();
        this.userId = booking.getUser().getId();
        this.busId = booking.getBusId();
        this.seatNumber = booking.getSeatNumber();
        this.bookingDate = booking.getBookingDate();
        this.status = booking.getBookingStatus();
    }
}
