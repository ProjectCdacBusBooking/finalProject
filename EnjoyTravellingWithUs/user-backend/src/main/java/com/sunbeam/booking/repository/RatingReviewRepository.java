package com.sunbeam.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunbeam.booking.entity.RatingReview;

@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview, Long> {

    /**
     * ✅ Retrieves all reviews for a specific bus.
     */
	@Query("SELECT r FROM RatingReview r JOIN FETCH r.user JOIN FETCH r.bus WHERE r.bus.id = :busId")
	List<RatingReview> findByBusId(Long busId);

    /**
     * ✅ Retrieves all reviews written by a specific user.
     */
	@Query("SELECT r FROM RatingReview r JOIN FETCH r.user JOIN FETCH r.bus WHERE r.user.id = :userId")
	List<RatingReview> findByUserId(Long userId);
}
