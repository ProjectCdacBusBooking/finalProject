package com.sunbeam.booking.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Bus ID is required")
    private Long busId;

    @NotNull(message = "Booking Date is required")
    private String bookingDate; // ✅ Store as String for safer parsing

    @NotEmpty(message = "Seat Number is required")
    private String seatNumber;

    private double price;

    @NotEmpty(message = "Status is required")
    private String status;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // ✅ Constructor with correct datetime format
    public BookingDTO(Long id, Long userId, Long busId, String bookingDateStr, String seatNumber, double price, String status) {
        this.id = id;
        this.userId = userId;
        this.busId = busId;
        this.bookingDate = formatDateTime(bookingDateStr); // ✅ Fix here
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
    }

    // ✅ Fixes datetime parsing
    private String formatDateTime(String dateTimeStr) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
            return dateTime.format(FORMATTER); // ✅ Formats correctly
        } catch (Exception e) {
            return LocalDateTime.now().format(FORMATTER); // ✅ Default to current time on error
        }
    }
}
