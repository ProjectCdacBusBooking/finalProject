package com.sunbeam.booking.service;

import com.sunbeam.booking.dto.RatingReviewDTO;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.entity.RatingReview;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.BusRepository;
import com.sunbeam.booking.repository.RatingReviewRepository;
import com.sunbeam.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {

    private static final Logger log = LoggerFactory.getLogger(RatingReviewServiceImpl.class);

    @Autowired
    private RatingReviewRepository ratingReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;

    /**
     * ✅ Adds a new rating and review.
     * - Ensures user and bus exist before saving.
     */
    @Override
    @Transactional
    public RatingReviewDTO addRatingReview(RatingReviewDTO ratingReviewDTO) {
        log.info("📌 Adding review for bus ID {} by user ID {}", ratingReviewDTO.getBusId(), ratingReviewDTO.getUserId());

        User user = userRepository.findById(ratingReviewDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Bus bus = busRepository.findById(ratingReviewDTO.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        RatingReview ratingReview = new RatingReview();
        ratingReview.setUser(user);
        ratingReview.setBus(bus);
        ratingReview.setRating(ratingReviewDTO.getRating());
        ratingReview.setReview(ratingReviewDTO.getReview());

        ratingReviewRepository.save(ratingReview);

        log.info("✅ Review added successfully for bus ID {}", bus.getId());

        return convertToDTO(ratingReview);
    }

    /**
     * ✅ Retrieves all reviews for a specific bus.
     */
    @Override
    public List<RatingReviewDTO> getReviewsByBus(Long busId) {
        log.info("📌 Fetching reviews for bus ID {}", busId);

        return ratingReviewRepository.findByBusId(busId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * ✅ Retrieves all reviews written by a specific user.
     */
    @Override
    public List<RatingReviewDTO> getReviewsByUser(Long userId) {
        log.info("📌 Fetching reviews by user ID {}", userId);

        return ratingReviewRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * ✅ Calculates the average rating for a bus.
     */
    @Override
    public double getAverageRating(Long busId) {
        log.info("📌 Calculating average rating for bus ID {}", busId);

        List<RatingReview> reviews = ratingReviewRepository.findByBusId(busId);
        if (reviews.isEmpty()) {
            log.warn("⚠️ No reviews found for bus ID {}", busId);
            return 0;
        }

        OptionalDouble averageRating = reviews.stream()
                .mapToInt(RatingReview::getRating)
                .average();

        return averageRating.orElse(0);
    }

    /**
     * ✅ Converts `RatingReview` entity to `RatingReviewDTO`.
     */
    private RatingReviewDTO convertToDTO(RatingReview review) {
        return new RatingReviewDTO(
                review.getId(),
                review.getUser().getId(),
                review.getBus().getId(),
                review.getRating(),
                review.getReview()
        );
    }
}
