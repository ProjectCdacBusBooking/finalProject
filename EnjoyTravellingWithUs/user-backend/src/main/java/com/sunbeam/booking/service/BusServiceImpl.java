package com.sunbeam.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.BookingRepository;
import com.sunbeam.booking.repository.BusRepository;
import com.sunbeam.booking.util.DTOMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FareCalculationService fareCalculationService;

    /**
     * ✅ Retrieves all buses.
     * - Uses `stream()` to efficiently convert entities to DTOs.
     */
    @Override
    public List<BusDTO> findAll() {
        return busRepository.findAll().stream()
                .map(this::convertToBusDTO)
                .collect(Collectors.toList());
    }

    /**
     * ✅ Finds a specific bus by its ID.
     * - Throws `ResourceNotFoundException` if the bus is not found.
     */
    @Override
    public BusDTO findBusDTOById(Long id) {
        return busRepository.findById(id)
            .map(DTOMapper::toBusDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));
    }


    /**
     * ✅ Saves a new bus or updates an existing one.
     */
    @Override
    @Transactional
    public BusDTO save(BusDTO busDTO) {
        Bus bus = convertToBus(busDTO);
        busRepository.save(bus);
        log.info("✅ Bus saved/updated: {}", bus.getId());
        return convertToBusDTO(bus);
    }

    /**
     * ✅ Deletes a bus by its ID.
     * - Ensures bus exists before deletion to avoid unnecessary database hits.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));
        busRepository.delete(bus);
        log.info("❌ Bus deleted: ID {}", id);
    }

    /**
     * ✅ Retrieves all bookings for all buses.
     * - Uses `stream()` to convert bookings into DTOs efficiently.
     */
    @Override
    public List<BookingDTO> findBookingsForAllBuses() {
        return bookingRepository.findAll().stream()
                .map(booking -> new BookingDTO(
                	    booking.getId(),
                	    booking.getUser().getId(),
                	    booking.getBus().getId(),
                	    booking.getBookingDate().toString(),
                	    booking.getSeatNumber(),
                	    booking.getPrice(),
                	    "CONFIRMED" // ✅ Add this missing status argument
                	)

            )    .collect(Collectors.toList());
    }

    /**
     * ✅ Searches for bus routes starting with the given prefix.
     * - Uses **case-insensitive** filtering for better user experience.
     */
    @Override
    public List<BusDTO> searchBusRoutesByPrefix(String prefix) {
        return busRepository.findByRouteStartingWith(prefix).stream()
                .map(this::convertToBusDTO)
                .collect(Collectors.toList());
    }

    /**
     * ✅ Calculates fare dynamically based on business logic.
     */
    @Override
    public double calculateFare(String source, String destination) {
        return fareCalculationService.calculateFare(source, destination);
    }

    /**
     * ✅ Converts a `Bus` entity to a `BusDTO`.
     */
    private BusDTO convertToBusDTO(Bus bus) {
        return new BusDTO(
                bus.getId(),
                bus.getName(),
                bus.getSource(),
                bus.getDestination(),
                bus.getDepartureTime().toString(),
                bus.getArrivalTime().toString(),
                bus.getBusNumber(),
                bus.getRoute(),
                bus.getCapacity(),
                bus.getTotalSeats(),
                bus.getAvailableSeats(),
                bus.getFare()
        );
    }

    /**
     * ✅ Converts a `BusDTO` to a `Bus` entity.
     */
    private Bus convertToBus(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setId(busDTO.getId());
        bus.setName(busDTO.getName());
        bus.setSource(busDTO.getSource());
        bus.setDestination(busDTO.getDestination());
        bus.setDepartureTime(busDTO.getDepartureTime());
        bus.setArrivalTime(busDTO.getArrivalTime());
        bus.setBusNumber(busDTO.getBusNumber());
        bus.setRoute(busDTO.getRoute());
        bus.setCapacity(busDTO.getCapacity());
        bus.setTotalSeats(busDTO.getTotalSeats());
        bus.setAvailableSeats(busDTO.getAvailableSeats());
        bus.setFare(busDTO.getFare());
        return bus;
    }
}
