package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusDTO {
	private Long id;
    private String busNumber;
    private String route;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    private int capacity;
}
