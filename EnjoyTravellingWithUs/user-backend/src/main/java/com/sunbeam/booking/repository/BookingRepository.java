package com.sunbeam.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sunbeam.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b JOIN FETCH b.user JOIN FETCH b.bus WHERE b.user.id = :userId")
	List<Booking> findByUserId(Long userId);


    boolean existsByBusIdAndSeatNumber(Long busId, String seatNumber);

}
