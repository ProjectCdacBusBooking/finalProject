package com.sandip.bus.pojo;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bus extends BaseEntity {

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "available_seats")
    private int  availableSeats;

    @Column(name = "bus_name", length = 255)
    private String busName;

    @Column(name = "bus_type", length = 255)
    private String busType;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "driver_name", length = 255)
    private String driverName;

    @Column(name = "fare_per_seat")
    private int farePerSeat;

    @Column(name = "route_from", length = 255)
    private String routeFrom;

    @Column(name = "route_to", length = 255)
    private String routeTo;

    @Column(name = "seats")
    private int seats;

//    @Column(name = "route_route_id")
//    private int routeRouteId;
}

