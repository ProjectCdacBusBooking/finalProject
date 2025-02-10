package com.sunbeam.booking.controller;

import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:3000") // Frontend la access denyasathi CORS enable karto
public class BusController {

    private static final Logger log = LoggerFactory.getLogger(BusController.class); // Logger initialize karto
    private final BusService busService; // Bus service inject karto

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/add")
    public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO) {
        log.info("📌 Adding new bus: {}", busDTO.getName()); // Navi bus add honar yachi log entry karto
        BusDTO newBus = busService.save(busDTO); // Navi bus database madhe save karto
        return ResponseEntity.status(HttpStatus.CREATED).body(newBus); // 201 CREATED status return karto
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        log.info("📌 Fetching all buses"); // Saglya buses chi mahiti get honar yachi log entry karto
        return ResponseEntity.ok(busService.findAll()); // Saglya buses cha list return karto
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable Long id) {
        log.info("📌 Fetching bus with ID: {}", id); // Specific bus ID varun ghetnyasathi log entry karto
        return ResponseEntity.ok(busService.findBusDTOById(id)); // Specific bus chi mahiti return karto
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        log.info("📌 Deleting bus with ID: {}", id); // Bus delete honar yachi log entry karto
        busService.deleteById(id); // ID varun bus delete karto
        return ResponseEntity.noContent().build(); // 204 No Content return karto
    }
}
