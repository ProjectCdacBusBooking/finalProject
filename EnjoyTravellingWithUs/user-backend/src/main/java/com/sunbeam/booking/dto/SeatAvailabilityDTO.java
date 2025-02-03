package com.sunbeam.booking.dto;

import lombok.Data;

@Data
public class SeatAvailabilityDTO {
    private int totalSeats;
    private int availableSeats;

}