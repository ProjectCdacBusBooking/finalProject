package com.sunbeam.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
}
