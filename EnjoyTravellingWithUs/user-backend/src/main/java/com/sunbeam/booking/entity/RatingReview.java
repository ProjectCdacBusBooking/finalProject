package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rating_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // User who gave the rating

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;  // Bus being rated
    
    @Column(nullable = false)
    private int rating;  // Rating between 1 and 5

    @Column(length = 500)
    private String review;  // Review text
}
