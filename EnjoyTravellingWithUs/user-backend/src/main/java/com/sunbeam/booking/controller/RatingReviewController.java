package com.sunbeam.booking.controller;

import com.sunbeam.booking.dto.RatingReviewDTO;
import com.sunbeam.booking.service.RatingReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Keeping React frontend compatibility
public class RatingReviewController {

    private final RatingReviewService ratingReviewService;

    public RatingReviewController(RatingReviewService ratingReviewService) {
        this.ratingReviewService = ratingReviewService;
    }

    /**
     * ✅ Adds a new rating and review
     */
    @PostMapping("/add")
    public ResponseEntity<RatingReviewDTO> addReview(@RequestBody RatingReviewDTO ratingReviewDTO) {
        RatingReviewDTO savedReview = ratingReviewService.addRatingReview(ratingReviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    /**
     * ✅ Retrieves all reviews for a specific bus
     */
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<RatingReviewDTO>> getReviewsByBus(@PathVariable Long busId) {
        return ResponseEntity.ok(ratingReviewService.getReviewsByBus(busId));
    }

    /**
     * ✅ Retrieves all reviews by a specific user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingReviewDTO>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingReviewService.getReviewsByUser(userId));
    }

    /**
     * ✅ Computes the average rating for a bus
     */
    @GetMapping("/average/{busId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long busId) {
        return ResponseEntity.ok(ratingReviewService.getAverageRating(busId));
    }
}
