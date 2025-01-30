package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private Long busId; // Book keleli bus ID

    @Column(nullable = false)
    private int selectedSeats; // Nivadlelya seats chi sankhya

    @Column(nullable = false)
    private String paymentMethod; // Payment method (Wallet, Credit Card, etc.)

    @Column(nullable = false)
    private String bookingStatus; // Booking status (Confirmed, Pending, Cancelled)
}
