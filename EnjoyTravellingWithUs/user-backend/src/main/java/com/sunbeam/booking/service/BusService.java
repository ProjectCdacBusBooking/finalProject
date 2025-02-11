package com.sunbeam.booking.service;

import java.util.List;

import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.dto.SeatDTO;

public interface BusService {

    /**
     * ✅ Retrieves all buses.
     */
    List<BusDTO> findAll();

    /**
     * ✅ Finds a specific bus by its ID.
     */
    BusDTO findBusDTOById(Long id);

    /**
     * ✅ Searches for bus routes starting with the given prefix.
     * - Returns a list of route names matching the prefix.
     */
    List<BusDTO> searchBusRoutesByPrefix(String prefix);

    /**
     * ✅ Saves a new bus or updates an existing one.
     */
    BusDTO save(BusDTO busDTO);

    /**
     * ✅ Deletes a bus by its ID.
     */
    void deleteById(Long id);

    /**
     * ✅ Calculates the fare between two locations.
     * - Uses business logic to compute the fare dynamically.
     */
    double calculateFare(String source, String destination);

    /**
     * ✅ Retrieves all bookings for all buses.
     */
    List<BookingDTO> findBookingsForAllBuses();
    
    List<BusDTO> searchBuses(String source, String destination, String date);
    
    List<SeatDTO> getSeatsByBusId(Long busId);
    
}
