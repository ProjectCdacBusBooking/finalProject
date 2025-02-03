package com.sunbeam.booking.dto;

import lombok.Data;

@Data
public class RatingReviewDTO {
    private Long userId;
    private Long busId;
    private int rating;
    private String review;

    // Getters and Setters
}
