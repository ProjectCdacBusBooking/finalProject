package com.sandip.bus.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandip.bus.dao.BusDao;
import com.sandip.bus.dao.ReservationDao;
import com.sandip.bus.dto.ApiResponse;
import com.sandip.bus.dto.BusDTO;
import com.sandip.bus.pojo.Bus;
import com.sandip.bus.pojo.Reservation;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BusServiceImpl  implements BusService{
	
	@Autowired
	private BusDao Busdao;
	
	@Autowired
	private ReservationDao resdao;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public ApiResponse Addbus(BusDTO bus) {
		
		Bus	busentity=modelmapper.map(bus, Bus.class);
		Bus persistent =Busdao.save(busentity);
		return new ApiResponse("success");
	}

	@Override
	public List<BusDTO> getAllBus() {
		List<Bus> allbus = Busdao.findAll();
//		BusDTO hh = modelmapper.map(allbus, BusDTO.class);
		List<BusDTO>busdto= new ArrayList<>();
		
		for (Bus bus : allbus) {
			
			busdto.add(	modelmapper.map(bus, BusDTO.class));
		}
		return busdto;
	}

	@Override
	public ApiResponse DeleteByid(Long id) {
		 Busdao.deleteById(id);
		
		return new ApiResponse("success");
	}
	
	
	

	@Override
	public List<Reservation> GetResrvation() {
		
		
		return resdao.findAll();
	}

}
