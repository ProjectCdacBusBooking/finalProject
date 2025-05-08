package com.sunbeam.booking.service;

import com.sunbeam.booking.dto.RatingReviewDTO;
import java.util.List;

<<<<<<< HEAD
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
=======
public interface RatingReviewService {
    RatingReviewDTO addRatingReview(RatingReviewDTO ratingReviewDTO);
    List<RatingReviewDTO> getReviewsByBus(Long busId);
    List<RatingReviewDTO> getReviewsByUser(Long userId);
    double getAverageRating(Long busId);
>>>>>>> 4592f26860dd1612aabb10cfb194f28a38b54c75
}
