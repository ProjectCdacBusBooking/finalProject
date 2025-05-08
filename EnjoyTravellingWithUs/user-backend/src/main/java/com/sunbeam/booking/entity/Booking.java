package com.sunbeam.booking.entity;

<<<<<<< Updated upstream
=======
<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalTime;

>>>>>>> Stashed changes
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
>>>>>>> 4592f26860dd1612aabb10cfb194f28a38b54c75
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
<<<<<<< HEAD
    
    private String bookingDate;
    private String seatNumber;
<<<<<<< Updated upstream
=======
    
    @Column(name = "destination", length = 255, nullable = false)
    private String destination;
    
    @Column(name = "fare", nullable = false)
    private int fare;
    
    @Column(name = "journey_date", nullable = false)
    private LocalDate journeyDate;
    
    @Column(name = "no_of_seats_booked", nullable = false)
    private Integer noOfSeatsBooked;
    
    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;
    
    @Column(name = "reservation_time", nullable = false)
    private LocalTime reservationTime;
    
    @Column(name = "reservation_type", length = 255, nullable = false)
    private String reservationType;
    
    @Column(name = "source", length = 255, nullable = false)
    private String source;
    
    
    
    @Column(name = "busnumber", length = 255, nullable = false)
    private String busnumber;
    
    
    
    
    
=======

    @Column(nullable = false, length = 10)
    private String seatNumber; // ✅ Using seatNumber instead of seat_id

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime bookingDate;
>>>>>>> 4592f26860dd1612aabb10cfb194f28a38b54c75
>>>>>>> Stashed changes
}
