package com.sandip.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandip.bus.dto.BusDTO;
import com.sandip.bus.pojo.Bus;
import com.sandip.bus.pojo.Reservation;
import com.sandip.bus.service.BusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Bus")
@CrossOrigin(origins = "http://localhost:3000")
class BusController {

	@Autowired
	private BusService  Busservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<?>AddBus(@RequestBody BusDTO bus){
		return ResponseEntity.status(HttpStatus.CREATED).body(Busservice.Addbus(bus));
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?>GetAll(){
		List<BusDTO> allbus=Busservice.getAllBus();
		
		return ResponseEntity.ok(allbus); 
	}
	
	@DeleteMapping()
	public ResponseEntity<?>DeleteBus(@RequestParam() Long id){
		System.out.println(id+"dfgvhbnjkm");
		
		
		return ResponseEntity.ok(Busservice.DeleteByid(id));
		
	}
	
	
	@GetMapping("/booking")
	public ResponseEntity<?>getAllBookings(){
		
		List<Reservation> are= Busservice.GetResrvation();
		
		return ResponseEntity.ok(are);
	}
	
}
