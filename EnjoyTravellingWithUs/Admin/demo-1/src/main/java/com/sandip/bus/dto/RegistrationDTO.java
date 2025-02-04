package com.sandip.bus.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistrationDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	
	
	
}
