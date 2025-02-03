package com.sunbeam.booking.dto;

import lombok.Data;

/**
 * 📝 SeatSelectionRequest - Seat Selection Request DTO
 * 📌 Seat Selection API च्या Request Body साठी वापरला जाणारा DTO.
 */
@Data
public class SeatSelectionRequest {
    private Long busId;
    private int selectedSeats;
}
