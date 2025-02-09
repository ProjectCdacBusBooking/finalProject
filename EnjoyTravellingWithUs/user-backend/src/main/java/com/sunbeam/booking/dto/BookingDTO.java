package com.sunbeam.booking.dto;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Bus ID is required")
    private Long busId;

    @NotNull(message = "Booking Date is required")
    private String bookingDateStr;

    @NotEmpty(message = "Seat Number is required")
    private String seatNumber;

    private double price;

    @NotEmpty(message = "Status is required")
    private String status;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // ✅ Converts `bookingDateStr` to LocalDateTime
    public LocalDateTime getBookingDate() {
        return LocalDateTime.parse(bookingDateStr.substring(0, 19), formatter);
    }
}
