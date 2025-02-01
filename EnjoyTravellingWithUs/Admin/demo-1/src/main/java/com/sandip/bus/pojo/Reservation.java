package com.sandip.bus.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation extends BaseEntity {

  

    @Column(name = "destination", length = 255, nullable = false)
    private String destination;

    @Column(name = "fare", nullable = false)
    private Integer fare;

    @Column(name = "journey_date", nullable = false)
    private LocalDate journeyDate;

    @Column(name = "no_of_seats_booked", nullable = false)
    private Integer noOfSeatsBooked;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "reservation_status", length = 255, nullable = false)
    private String reservationStatus;

    @Column(name = "reservation_time", nullable = false)
    private LocalTime reservationTime;

    @Column(name = "reservation_type", length = 255, nullable = false)
    private String reservationType;

    @Column(name = "source", length = 255, nullable = false)
    private String source;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
