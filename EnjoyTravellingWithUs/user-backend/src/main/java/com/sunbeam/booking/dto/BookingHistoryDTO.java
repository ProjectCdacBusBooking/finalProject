package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingHistoryDTO {
    private int bookingId;
    private String date;
    private String seatNumbers;

}