package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "rating_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "bus"}) // ✅ Prevents infinite recursion
public class RatingReview extends BaseEntity { // ✅ Now extends BaseEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // User who gave the rating

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;  // Bus being rated
    
    @Min(1) @Max(5) // ✅ Ensures valid rating between 1 and 5
    @Column(nullable = false)
    private int rating;  // Rating between 1 and 5

    @Column(length = 500)
    private String review;  // Review text
}
