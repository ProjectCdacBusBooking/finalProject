package com.sunbeam.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.dto.SeatDTO;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.service.BusService;

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
    public ResponseEntity<?> getBusById(@PathVariable Long id) {
        try {
            log.info("📌 Fetching bus with ID: {}", id);
            BusDTO busDTO = busService.findBusDTOById(id);
            return ResponseEntity.ok(busDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("🚫 Bus not found!");
        } catch (Exception e) {
            log.error("❌ Error fetching bus details:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("⚠️ Internal Server Error");
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        log.info("📌 Deleting bus with ID: {}", id); // Bus delete honar yachi log entry karto
        busService.deleteById(id); // ID varun bus delete karto
        return ResponseEntity.noContent().build(); // 204 No Content return karto
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<BusDTO>> searchBuses(
        @RequestParam String source,
        @RequestParam String destination,
        @RequestParam String date) {
        
        log.info("📌 Searching buses from {} to {} on {}", source, destination, date);
        
        List<BusDTO> buses = busService.searchBuses(source, destination, date);
        
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/{busId}/seats")
    public ResponseEntity<List<SeatDTO>> getSeatsByBusId(@PathVariable Long busId) {
        log.info("📌 Fetching seats for Bus ID: {}", busId);
        List<SeatDTO> seats = busService.getSeatsByBusId(busId);
        return ResponseEntity.ok(seats);
    }



}
