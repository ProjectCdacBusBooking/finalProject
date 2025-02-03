package com.sunbeam.booking.dto;

import lombok.Data;

@Data
public class BookingConfirmationDTO {
    private int busId;
    private String date;
    private String seatNumbers;  // Comma separated seat numbers
    private String status;
    private String message;

}