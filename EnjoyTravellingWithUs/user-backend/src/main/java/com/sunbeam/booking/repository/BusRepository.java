package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.sunbeam.booking.entity.Bus;

/**
 * 📝 BusRepository - Bus related Database Queries handle karto
 * 📌 Custom queries jevn ki source ani destination nusar bus shodhanyasathi.
 */
public interface BusRepository extends JpaRepository<Bus, Long> {
    
    /**
     * ✅ Find Buses by Source and Destination
     * 📌 DB madhun buses filter karnya sathi jevn ki **source ani destination** match hotil.
     */
    List<Bus> findBySourceAndDestination(String source, String destination);
}
