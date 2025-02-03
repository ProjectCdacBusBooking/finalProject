package com.sunbeam.booking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.repository.BusRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> searchBuses(String source, String destination) {
        return busRepository.findBySourceAndDestination(source, destination);
    }

    public Bus getBusDetails(Long busId) {
        return busRepository.findById(busId).orElse(null);
    }
}
