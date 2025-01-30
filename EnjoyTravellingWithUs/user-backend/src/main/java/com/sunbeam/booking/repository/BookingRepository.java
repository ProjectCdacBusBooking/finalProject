package com.sunbeam.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.booking.entity.Booking;

/**
 * 📝 BookingRepository - Booking sambandhit Database Queries
 * 📌 Booking nusar DB che CRUD operations.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Future madhe custom booking queries add karaychya astil tar ithe add kara
	
	 /**
     * ✅ Get All Bookings by User ID
     */
    List<Booking> findByUserId(Long userId);
}
