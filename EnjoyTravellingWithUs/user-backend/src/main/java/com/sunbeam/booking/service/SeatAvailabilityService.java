package com.sunbeam.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.SeatAvailabilityDTO;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.repository.BusRepository;

@Service
public class SeatAvailabilityService {

    @Autowired
    private BusRepository busRepository;

    public SeatAvailabilityDTO checkSeatAvailability(Long busId) {
        Bus bus = busRepository.findById(busId).orElse(null);
        if (bus != null) {
            return new SeatAvailabilityDTO(bus.getTotalSeats(), bus.getAvailableSeats());
        }
        return null;
    }
}
