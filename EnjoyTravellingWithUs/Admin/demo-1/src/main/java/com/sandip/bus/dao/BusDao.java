package com.sandip.bus.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandip.bus.pojo.Bus;

public interface BusDao extends JpaRepository<Bus, Long> {

}
