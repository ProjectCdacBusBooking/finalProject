package com.sunbeam.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.booking.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findByRouteContaining(String route);
}
