package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.booking.entity.Booking;

/**
 * 📝 BookingRepository - Booking sambandhit Database Queries
 * 📌 Booking nusar DB che CRUD operations.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Future madhe custom booking queries add karaychya astil tar ithe add kara
}
