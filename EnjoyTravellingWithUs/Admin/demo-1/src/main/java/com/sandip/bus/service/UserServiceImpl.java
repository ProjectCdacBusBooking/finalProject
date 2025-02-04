package com.sandip.bus.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandip.bus.custom_exception.AuthenticationException;
import com.sandip.bus.dao.AdminDao;
import com.sandip.bus.dao.UserDao;
import com.sandip.bus.dto.ApiResponse;
import com.sandip.bus.dto.AuthRequest;
import com.sandip.bus.dto.RegistrationDTO;
import com.sandip.bus.pojo.Admin;
import com.sandip.bus.pojo.User;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userdao;
	

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ApiResponse Registration(RegistrationDTO Rdto) {
		Admin a=modelMapper.map(Rdto,Admin.class);
		Admin Adminpersistent=	adminDao.save(a);
		return new ApiResponse("success");
	}

	@Override
	public RegistrationDTO login(AuthRequest authrequst) {
	Admin admin=	adminDao.findByEmailAndPassword(authrequst.getEmail(), authrequst.getPassword())
		.orElseThrow(()->new AuthenticationException("Invalid Email or password !!!!"));
	
		return modelMapper.map(admin, RegistrationDTO.class);
	}

	@Override
	public List<User> GetAllUeser() {
	
	
		return 	userdao.findAll();
	}

}
