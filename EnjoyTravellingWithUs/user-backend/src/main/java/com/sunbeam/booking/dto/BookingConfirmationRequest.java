package com.sunbeam.booking.dto;

import lombok.Data;

/**
 * 📝 BookingConfirmationRequest - Booking Confirmation Request DTO
 * 📌 Booking Confirmation API च्या Request Body साठी वापरला जाणारा DTO.
 */
@Data
public class BookingConfirmationRequest {
    private Long busId;
    private int selectedSeats;
    private String paymentMethod; // Example: "wallet", "credit card", etc.
}
