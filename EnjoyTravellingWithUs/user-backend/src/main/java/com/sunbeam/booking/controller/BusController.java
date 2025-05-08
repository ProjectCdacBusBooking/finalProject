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
@CrossOrigin(origins = "http://localhost:3000") // ✅ Keeping React frontend compatibility
public class BusController {

    private static final Logger log = LoggerFactory.getLogger(BusController.class);
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/add")
    public ResponseEntity<BusDTO> addBus(@RequestBody BusDTO busDTO) {
        log.info("📌 Adding new bus: {}", busDTO.getName());
        BusDTO newBus = busService.save(busDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBus);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        return ResponseEntity.ok(busService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.findBusDTOById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
