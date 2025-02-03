package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // User who made the booking

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;  // Bus being booked

    @Column(nullable = false)
    private String seatNumber; // Selected seat number(s)

    @Column(nullable = false)
    private String paymentMethod; // Payment Method (Wallet, UPI, etc.)

    @Column(nullable = false)
    private String bookingStatus; // Confirmed, Pending, Cancelled

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate = new Date(); // Default booking date set
}
