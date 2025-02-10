package com.sunbeam.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.booking.service.FareCalculationService;

@RestController
@RequestMapping("/api/fare") // Bus fare calculation related APIs handle karnyasathi controller
@CrossOrigin(origins = "http://localhost:3000") // Frontend access allow karnyasathi CORS enable karto
public class FareCalculationController {

    private final FareCalculationService fareCalculationService; // Fare calculation service inject karto

    public FareCalculationController(FareCalculationService fareCalculationService) {
        this.fareCalculationService = fareCalculationService;
    }

    /**
     * ✅ Source ani destination na fare calculate karto
     */
    @GetMapping("/calculate")
    public ResponseEntity<Double> getFare(@RequestParam String source, @RequestParam String destination) {
        double fare = fareCalculationService.calculateFare(source, destination); // Source ani destination nusar fare calculate karto
        return ResponseEntity.ok(fare); // Calculated fare return karto
    }
}
