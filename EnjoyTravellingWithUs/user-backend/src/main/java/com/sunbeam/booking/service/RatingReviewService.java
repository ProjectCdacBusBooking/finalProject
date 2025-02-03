package com.sunbeam.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.RatingReviewDTO;
import com.sunbeam.booking.entity.RatingReview;
import com.sunbeam.booking.repository.RatingReviewRepository;

@Service
public class RatingReviewService {

    @Autowired
    private RatingReviewRepository ratingReviewRepository;

    public RatingReview addRatingReview(RatingReviewDTO ratingReviewDTO) {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setUserId(ratingReviewDTO.getUserId());
        ratingReview.setBusId(ratingReviewDTO.getBusId());
        ratingReview.setRating(ratingReviewDTO.getRating());
        ratingReview.setReview(ratingReviewDTO.getReview());

        return ratingReviewRepository.save(ratingReview);
    }
}
