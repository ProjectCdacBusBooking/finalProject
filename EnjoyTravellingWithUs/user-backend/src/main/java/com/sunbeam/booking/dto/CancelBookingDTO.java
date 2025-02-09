package com.sunbeam.booking.dto;

import lombok.Data;

@Data
public class CancelBookingDTO {
    private Long bookingId; // ✅ Changed to Long for consistency
}
