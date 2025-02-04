package com.sandip.bus.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {

	
	private String msg;
	private LocalDateTime datetime;
	
	
public 	ApiResponse(String msg){	
	this.msg=msg;
	this.datetime=LocalDateTime.now();
	
	}
}
