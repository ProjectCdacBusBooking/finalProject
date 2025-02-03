package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String busNumber;
    private String route;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private int capacity;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    
}
