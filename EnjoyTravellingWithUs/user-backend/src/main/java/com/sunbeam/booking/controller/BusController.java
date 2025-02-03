package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.service.BusService;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:3000")
public class BusController {
    @Autowired
    private BusService busService;

    @GetMapping("/all")
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        List<BusDTO> buses = busService.findAll();
        return ResponseEntity.ok(buses);
    }

    @PostMapping
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        bus.setAvailableSeats(bus.getCapacity());
        Bus newBus = busService.save(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable Long id) {
        BusDTO bus = busService.findBusDTOById(id);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus busDetails) {
        Bus updatedBus = busService.updateBus(id, busDetails);
        if (updatedBus != null) {
            return ResponseEntity.ok(updatedBus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search-routes")
    public ResponseEntity<List<String>> searchBusRoutesByPrefix(@RequestParam String prefix) {
        List<String> routes = busService.searchBusRoutesByPrefix(prefix);
        return ResponseEntity.ok(routes);
    }
}
