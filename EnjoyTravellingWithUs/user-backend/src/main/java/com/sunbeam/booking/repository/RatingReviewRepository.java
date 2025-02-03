package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunbeam.booking.entity.RatingReview;

public interface RatingReviewRepository extends JpaRepository<RatingReview, Long> {
    // Custom queries can be added here if needed
}

