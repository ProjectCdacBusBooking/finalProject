package com.sunbeam.booking.dto;

import lombok.Data;

@Data
public class BookingConfirmationRequest {
    private Long busId;
    private int selectedSeats;
    private String paymentMethod; // Example: "wallet", "credit card", etc.
}
