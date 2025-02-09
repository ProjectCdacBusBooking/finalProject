package com.sunbeam.booking.service;

import com.sunbeam.booking.dto.RatingReviewDTO;
import java.util.List;

public interface RatingReviewService {
    RatingReviewDTO addRatingReview(RatingReviewDTO ratingReviewDTO);
    List<RatingReviewDTO> getReviewsByBus(Long busId);
    List<RatingReviewDTO> getReviewsByUser(Long userId);
    double getAverageRating(Long busId);
}
