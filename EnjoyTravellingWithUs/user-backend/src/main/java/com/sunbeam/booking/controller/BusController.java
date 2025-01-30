package com.sunbeam.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
