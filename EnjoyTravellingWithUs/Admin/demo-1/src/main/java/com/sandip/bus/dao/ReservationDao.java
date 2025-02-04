package com.sandip.bus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandip.bus.pojo.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

}
