package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.service.BusService;

import lombok.RequiredArgsConstructor;

/**
 * 📝 BusController - Bus संबंधित API Handlers
 * 📌 Bus Search, Bus Details, Seat Availability, Seat Selection, Booking Management API आहेत.
 */
@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;
    
    
    /**
     * ✅ Search Buses API
     * 📌 Source, Destination, Date नुसार बसा ची शोध घेते.
     * 🟢 URL: GET /api/buses/search?source={source}&destination={destination}&date={date}
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchBuses(@RequestParam String source, @RequestParam String destination, @RequestParam String date) {
        List<String> buses = busService.searchBuses(source, destination, date);
        if (!buses.isEmpty()) {
            return ResponseEntity.ok(buses);
        } else {
            return ResponseEntity.status(404).body("❌ No buses found for the given search!");
        }
    }
    
    

    /**
     * ✅ Get Bus Details API
     * 📌 Bus ID नुसार बसा ची तपशील माहिती मिळवते.
     * 🟢 URL: GET /api/buses/{busId}
     */
    @GetMapping("/{busId}")
    public ResponseEntity<?> getBusDetails(@PathVariable Long busId) {
        String busDetails = busService.getBusDetails(busId);
        if (busDetails != null) {
            return ResponseEntity.ok(busDetails);
        } else {
            return ResponseEntity.status(404).body("❌ Bus not found!");
        }
    }
    
    /**
     * ✅ Check Seat Availability API
     * 📌 Bus ID नुसार सीट उपलब्धता तपासते.
     * 🟢 URL: GET /api/buses/{busId}/seats
     */
    @GetMapping("/{busId}/seats")
    public ResponseEntity<?> checkSeatAvailability(@PathVariable Long busId) {
        String seatAvailability = busService.checkSeatAvailability(busId);
        if (seatAvailability != null) {
            return ResponseEntity.ok(seatAvailability);
        } else {
            return ResponseEntity.status(404).body("❌ Bus not found or No Seats Available!");
        }
    }
}
