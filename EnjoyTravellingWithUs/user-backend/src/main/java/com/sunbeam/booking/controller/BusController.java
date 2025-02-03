package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.service.BusService;

@RestController
@RequestMapping("/bus")
@CrossOrigin(origins = "http://localhost:3000")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/search")
    public ResponseEntity<List<Bus>> searchBuses(@RequestParam String source, @RequestParam String destination, @RequestParam String date) {
        List<Bus> buses = busService.searchBuses(source, destination, date);
        if (!buses.isEmpty()) {
            return ResponseEntity.ok(buses);
        } else {
            return ResponseEntity.status(404).body("❌ No buses found for the given search!");
        }
    }

    @GetMapping("/{busId}")
    public ResponseEntity<Bus> getBusDetails(@PathVariable Long busId) {
        Bus busDetails = busService.getBusDetails(busId);
        if (busDetails != null) {
            return ResponseEntity.ok(busDetails);
        } else {
            return ResponseEntity.status(404).body("❌ Bus not found!");
        }
    }

    @GetMapping("/{busId}/seats")
    public ResponseEntity<Integer> checkSeatAvailability(@PathVariable Long busId) {
        int seatAvailability = busService.checkSeatAvailability(busId);
        if (seatAvailability >= 0) {
            return ResponseEntity.ok(seatAvailability);
        } else {
            return ResponseEntity.status(404).body("❌ Bus not found or No Seats Available!");
        }
    }
}
