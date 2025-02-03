package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatAvailabilityDTO {
    private int totalSeats;
    private int availableSeats;
}
