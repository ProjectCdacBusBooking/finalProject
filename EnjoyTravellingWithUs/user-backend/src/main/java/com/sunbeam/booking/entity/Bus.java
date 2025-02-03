package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Bus ID (Primary Key)

    @Column(nullable = false)
    private String name; // Bus Name

    @Column(nullable = false)
    private String source; // Source location (e.g., Mumbai)

    @Column(nullable = false)
    private String destination; // Destination location (e.g., Pune)

    @Column(nullable = false)
    private String departureTime; // Departure time (HH:mm format)

    @Column(nullable = false)
    private int totalSeats; // Total number of seats

    @Column(nullable = false)
    private int availableSeats; // Available seats in the bus

    @Column(nullable = false)
    private double fare; // Ticket price
}
