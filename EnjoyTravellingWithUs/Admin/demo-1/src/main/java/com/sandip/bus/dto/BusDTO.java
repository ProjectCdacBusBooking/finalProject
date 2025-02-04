package com.sandip.bus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusDTO {
		
		private Long id;
	    private String busName;
	    private String busType;
	    private String driverName;
	    private int seats;
	
}
