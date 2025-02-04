package com.sandip.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandip.bus.dao.AdminDao;
import com.sandip.bus.dto.AuthRequest;
import com.sandip.bus.dto.RegistrationDTO;
import com.sandip.bus.pojo.Admin;
import com.sandip.bus.service.UserService;

@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	
	
	@Autowired 
	private UserService userService;
	
	
	@PostMapping("/reg")
	public ResponseEntity<?> Registration(@RequestBody RegistrationDTO u){	
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.Registration(u));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?>Login(@RequestBody AuthRequest authrequst){
		return ResponseEntity.ok(userService.login(authrequst));
	}
	
	@GetMapping("/user")
	public ResponseEntity<?>GetAllUser(){
		return ResponseEntity.ok(userService.GetAllUeser());
	}
	

}
