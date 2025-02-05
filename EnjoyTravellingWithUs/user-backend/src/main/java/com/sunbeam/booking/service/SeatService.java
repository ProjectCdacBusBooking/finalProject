package com.sunbeam.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.entity.Seat;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.SeatRepository;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAvailableSeats(Long busId) {
        return seatRepository.findByBusIdAndAvailable(busId, true);
    }

    public void updateSeatAvailability(Long seatId, boolean available) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new ResourceNotFoundException("Seat not found"));
        seat.setAvailable(available);
        seatRepository.save(seat);
    }


}
