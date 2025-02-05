package com.sunbeam.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.booking.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByBusId(Long busId);
    List<Seat> findByBusIdAndAvailable(Long busId, boolean available);
    Optional<Seat> findByBusIdAndSeatNumber(Long busId, String seatNumber);
}
