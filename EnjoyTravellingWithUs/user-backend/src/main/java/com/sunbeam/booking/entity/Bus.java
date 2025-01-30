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
    private String name; // Bus che naav

    @Column(nullable = false)
    private String source; // Suratnache thikaan (udaharan: Mumbai)

    @Column(nullable = false)
    private String destination; // Pochanyache thikaan (udaharan: Pune)

    @Column(nullable = false)
    private String departureTime; // Suratnyachi vel (HH:mm format)

    @Column(nullable = false)
    private int totalSeats; // Bus madhil ekun seats

    @Column(nullable = false)
    private int availableSeats; // Upyog karti seats

    @Column(nullable = false)
    private double fare; // Tikeet price
}
