package com.sunbeam.booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingConfirmationRequest {
    
    @NotNull(message = "Bus ID is required")
    private Long busId;

    @Min(value = 1, message = "At least one seat must be selected")
    private int selectedSeats;

    @NotEmpty(message = "Payment method is required")
    private String paymentMethod; // Example: "wallet", "credit card"
}
