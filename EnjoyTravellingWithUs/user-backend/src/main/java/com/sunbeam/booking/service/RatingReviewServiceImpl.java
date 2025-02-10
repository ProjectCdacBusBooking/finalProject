package com.sunbeam.booking.service;  
// 📌 Service layer madhil package declaration  

import com.sunbeam.booking.dto.RatingReviewDTO;  
// 📌 DTO (Data Transfer Object) import kela  

import com.sunbeam.booking.entity.Bus;  
import com.sunbeam.booking.entity.RatingReview;  
import com.sunbeam.booking.entity.User;  
// 📌 Required entities import kele  

import com.sunbeam.booking.exceptions.ResourceNotFoundException;  
// 📌 Custom exception **ResourceNotFoundException** import kela  

import com.sunbeam.booking.repository.BusRepository;  
import com.sunbeam.booking.repository.RatingReviewRepository;  
import com.sunbeam.booking.repository.UserRepository;  
// 📌 Required repositories inject karnya sathi import kele  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import jakarta.transaction.Transactional;  
// 📌 Dependency injection aani transactional annotation import kele  

import java.util.List;  
import java.util.OptionalDouble;  
import java.util.stream.Collectors;  
// 📌 Collections aani stream API vaprayla imports kele  

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
// 📌 Logging sathi SLF4J library import kela  

@Service  
// 📌 Ha class ek **Spring Service Bean** ahe  
public class RatingReviewServiceImpl implements RatingReviewService {  

    private static final Logger log = LoggerFactory.getLogger(RatingReviewServiceImpl.class);  
    // 📌 Logger **debugging aani tracking sathi** create kela  

    @Autowired  
    private RatingReviewRepository ratingReviewRepository;  

    @Autowired  
    private UserRepository userRepository;  

    @Autowired  
    private BusRepository busRepository;  
    // 📌 **Repositories inject kele** - DB madhun data fetch karnya sathi  

    /**
     * ✅ Adds a new rating and review.
     * - Ensures user and bus exist before saving.
     */
    @Override  
    @Transactional  
    public RatingReviewDTO addRatingReview(RatingReviewDTO ratingReviewDTO) {  
        log.info("📌 Adding review for bus ID {} by user ID {}", ratingReviewDTO.getBusId(), ratingReviewDTO.getUserId());  
        // 📌 **Review add honar aahe he log madhun track karto**  

        User user = userRepository.findById(ratingReviewDTO.getUserId())  
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));  
        // 📌 **User ID exist nahi tar exception throw hoto**  

        Bus bus = busRepository.findById(ratingReviewDTO.getBusId())  
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));  
        // 📌 **Bus ID exist nahi tar exception throw hoto**  

        RatingReview ratingReview = new RatingReview();  
        ratingReview.setUser(user);  
        ratingReview.setBus(bus);  
        ratingReview.setRating(ratingReviewDTO.getRating());  
        ratingReview.setReview(ratingReviewDTO.getReview());  
        // 📌 **New rating review object create kela**  

        ratingReviewRepository.save(ratingReview);  
        // 📌 **Database madhe save kela**  

        log.info("✅ Review added successfully for bus ID {}", bus.getId());  
        // 📌 **Review successfully add jhala he confirm karto**  

        return convertToDTO(ratingReview);  
        // 📌 **Saved entity DTO format madhun return karto**  
    }  

    /**
     * ✅ Retrieves all reviews for a specific bus.
     */
    @Override  
    public List<RatingReviewDTO> getReviewsByBus(Long busId) {  
        log.info("📌 Fetching reviews for bus ID {}", busId);  
        // 📌 **Konte bus la kiti review aahet he fetch karto**  

        return ratingReviewRepository.findByBusId(busId).stream()  
                .map(this::convertToDTO)  
                .collect(Collectors.toList());  
        // 📌 **Reviews map karnya sathi stream API use keli**  
    }  

    /**
     * ✅ Retrieves all reviews written by a specific user.
     */
    @Override  
    public List<RatingReviewDTO> getReviewsByUser(Long userId) {  
        log.info("📌 Fetching reviews by user ID {}", userId);  
        // 📌 **User ne dilele sagle reviews fetch karto**  

        return ratingReviewRepository.findByUserId(userId).stream()  
                .map(this::convertToDTO)  
                .collect(Collectors.toList());  
        // 📌 **Reviews map karnya sathi stream API use keli**  
    }  

    /**
     * ✅ Calculates the average rating for a bus.
     */
    @Override  
    public double getAverageRating(Long busId) {  
        log.info("📌 Calculating average rating for bus ID {}", busId);  
        // 📌 **Bus cha average rating calculate karto**  

        List<RatingReview> reviews = ratingReviewRepository.findByBusId(busId);  
        if (reviews.isEmpty()) {  
            log.warn("⚠️ No reviews found for bus ID {}", busId);  
            return 0;  
            // 📌 **Reviews nasel tar warning log karto aani 0 return karto**  
        }  

        OptionalDouble averageRating = reviews.stream()  
                .mapToInt(RatingReview::getRating)  
                .average();  
        // 📌 **Stream madhun sagle ratings ghetle aani average calculate kela**  

        return averageRating.orElse(0);  
        // 📌 **OptionalDouble madhun value extract keli**  
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
        // 📌 **Entity to DTO convert karnya sathi helper method**  
    }  
}  
