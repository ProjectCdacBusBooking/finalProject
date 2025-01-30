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
    private Long id; // Booking ID (Primary Key)
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long busId; // Book keleli bus ID

    @Column(nullable = false)
    private int selectedSeats; // Nivadlelya seats chi sankhya

    @Column(nullable = false)
    private String seatNumber; // Nivadlelya seat number

    @Column(nullable = false)
    private String paymentMethod; // Payment method (Wallet, Credit Card, etc.)

    @Column(nullable = false)
    private String bookingStatus; // Booking status (Confirmed, Pending, Cancelled)

    @Column(nullable = false)
    private Date bookingDate; // Booking date (Date type)
}
