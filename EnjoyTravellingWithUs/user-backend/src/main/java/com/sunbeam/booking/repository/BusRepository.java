package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.booking.entity.Bus;

/**
 * 📝 BusRepository - Bus संबंधित Database Queries
 * 📌 Bus नुसार DB चे CRUD ऑपरेशन्स.
 */
public interface BusRepository extends JpaRepository<Bus, Long> {
    // Placeholder for future bus-specific database queries
}
