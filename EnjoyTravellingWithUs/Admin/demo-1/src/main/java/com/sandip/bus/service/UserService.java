package com.sandip.bus.service;

import java.util.List;

import com.sandip.bus.dto.ApiResponse;
import com.sandip.bus.dto.AuthRequest;
import com.sandip.bus.dto.RegistrationDTO;
import com.sandip.bus.pojo.User;

public interface UserService {

	public ApiResponse Registration(RegistrationDTO Rdto) ;

	public RegistrationDTO login(AuthRequest authrequst);

	public List<User> GetAllUeser();
	
}
