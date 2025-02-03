package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.RatingReviewDTO;
import com.sunbeam.booking.entity.RatingReview;
import com.sunbeam.booking.service.RatingReviewService;

@RestController
@RequestMapping("/ratings-reviews")
public class RatingReviewController {

    @Autowired
    private RatingReviewService ratingReviewService;

    @PostMapping
    public RatingReview addRatingReview(@RequestBody RatingReviewDTO ratingReviewDTO) {
        return ratingReviewService.addRatingReview(ratingReviewDTO);
    }
}
