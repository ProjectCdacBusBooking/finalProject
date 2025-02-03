package com.sunbeam.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.repository.BusRepository;

import lombok.RequiredArgsConstructor;

/**
 * 📝 BusService - Bus related logic manage karto
 * 📌 Bus Search, Bus Details, Seat Availability, Booking related logic yethun handle hoil.
 */
@Service
@RequiredArgsConstructor
public class BusService {

	@Autowired
    private final BusRepository busRepository;

    /**
     * ✅ Search Buses Method
     * 📌 Source, Destination nusar **DB madhun** buses fetch karto.
     */
    public List<Bus> searchBuses(String source, String destination, String date) {
        return busRepository.findBySourceAndDestination(source, destination);
    }

    /**
     * ✅ Get Bus Details Method
     * 📌 Bus ID nusar **DB madhun** bus details fetch karto.
     */
    public Bus getBusDetails(Long busId) {
        return busRepository.findById(busId).orElse(null);
    }

    /**
     * ✅ Check Seat Availability Method
     * 📌 Bus madhil **available seats count return karto.**
     */
    public int checkSeatAvailability(Long busId) {
        Bus bus = busRepository.findById(busId).orElse(null);
        return (bus != null) ? bus.getAvailableSeats() : -1;
    }
}
