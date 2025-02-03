package com.sunbeam.booking.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private Long id;

    @NotEmpty(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Bus ID is required")
    private Long busId;

    @NotEmpty(message = "Booking Date is required")
    private String bookingDate;

    @NotEmpty(message = "Seat Number is required")
    private String seatNumber;
}
