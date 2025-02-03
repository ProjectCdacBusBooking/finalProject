package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.SeatAvailabilityDTO;

@Service
public class SeatAvailabilityService {

    public SeatAvailabilityDTO checkSeatAvailability(int busId, String date) {
        // For the sake of this example, we will return a mock seat availability.
        // This should be fetched from the database with actual logic.
        
        SeatAvailabilityDTO seatAvailabilityDTO = new SeatAvailabilityDTO();
        seatAvailabilityDTO.setTotalSeats(40);
        seatAvailabilityDTO.setAvailableSeats(15); // Mock data, implement real logic later
        return seatAvailabilityDTO;
    }
}
