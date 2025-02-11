package com.sunbeam.booking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sunbeam.booking.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    /**
     * ✅ Optimized Query to Fetch Buses by Route Prefix (Case-Insensitive)
     * 
     * - Uses **LOWER(b.route)** to ensure case-insensitive matching.
     * - Uses `LIKE` with `CONCAT(:prefix, '%')` to match routes starting with the given prefix.
     * - Improves **user experience** by allowing searches without worrying about letter case.
     */
    @Query("SELECT b FROM Bus b WHERE LOWER(b.route) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Bus> findByRouteStartingWith(String prefix);
    
    List<Bus> findBySourceAndDestination(String source, String destination);
    
    @Query("SELECT b FROM Bus b LEFT JOIN FETCH b.seats WHERE b.id = :busId")
    Optional<Bus> findByIdWithSeats(@Param("busId") Long busId);

}
