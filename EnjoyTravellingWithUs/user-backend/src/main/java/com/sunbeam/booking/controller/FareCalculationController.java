package com.sunbeam.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.booking.service.FareCalculationService;

@RestController
@RequestMapping("/api/fare")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Keeping React frontend compatibility
public class FareCalculationController {

    private final FareCalculationService fareCalculationService;

    public FareCalculationController(FareCalculationService fareCalculationService) {
        this.fareCalculationService = fareCalculationService;
    }

    /**
     * ✅ Calculates the fare between source and destination.
     */
    @GetMapping("/calculate")
    public ResponseEntity<Double> getFare(@RequestParam String source, @RequestParam String destination) {
        double fare = fareCalculationService.calculateFare(source, destination);
        return ResponseEntity.ok(fare);
    }
}
