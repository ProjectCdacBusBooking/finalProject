package com.sandip.bus.service;

import java.util.List;

import com.sandip.bus.dto.ApiResponse;
import com.sandip.bus.dto.BusDTO;
import com.sandip.bus.pojo.Bus;
import com.sandip.bus.pojo.Reservation;

public interface BusService {

	ApiResponse Addbus(BusDTO bus);

	List<BusDTO> getAllBus();

	ApiResponse DeleteByid(Long id);

	List<Reservation> GetResrvation() ;

}
