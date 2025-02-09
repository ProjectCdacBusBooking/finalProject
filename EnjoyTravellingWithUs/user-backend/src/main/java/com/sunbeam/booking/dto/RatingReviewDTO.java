package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingReviewDTO {
    private Long id;
    private Long userId;
    private Long busId;
    private int rating;
    private String review;
}
