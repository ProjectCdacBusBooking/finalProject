package com.sunbeam.booking.controller;

import com.sunbeam.booking.dto.RatingReviewDTO;
import com.sunbeam.booking.service.RatingReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews") // Ya controller madhun rating ani review sambandhit API endpoints handle kele jatat
@CrossOrigin(origins = "http://localhost:3000") // Frontend access allow karayla CORS enable kele ahe
public class RatingReviewController {

    private final RatingReviewService ratingReviewService; // RatingReviewService chi dependency inject keli ahe

    public RatingReviewController(RatingReviewService ratingReviewService) {
        this.ratingReviewService = ratingReviewService;
    }

    /**
     * ✅ Naveen rating ani review add karto
     */
    @PostMapping("/add")
    public ResponseEntity<RatingReviewDTO> addReview(@RequestBody RatingReviewDTO ratingReviewDTO) {
        RatingReviewDTO savedReview = ratingReviewService.addRatingReview(ratingReviewDTO); // Review save karnya sathi service call karto
        return ResponseEntity.ok(savedReview); // Response madhe saved review return karto
    }

    /**
     * ✅ Ekadya bus sathi saglya reviews milavto
     */
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<RatingReviewDTO>> getReviewsByBus(@PathVariable Long busId) {
        return ResponseEntity.ok(ratingReviewService.getReviewsByBus(busId)); // Bus ID nusar saglya reviews return karto
    }

    /**
     * ✅ Ekadya user ne dilelya saglya reviews milavto
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingReviewDTO>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingReviewService.getReviewsByUser(userId)); // User ID nusar tyane dilelya reviews return karto
    }

    /**
     * ✅ Ekadya bus sathi average rating calculate karto
     */
    @GetMapping("/average/{busId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long busId) {
        return ResponseEntity.ok(ratingReviewService.getAverageRating(busId)); // Bus ID nusar average rating return karto
    }
}
