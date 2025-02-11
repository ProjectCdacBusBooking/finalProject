package com.sunbeam.booking.service;  
// 📌 He package declaration ahe, jo service layer la indicate karto  

import java.util.List;
// 📌 List import keli jo multiple objects handle karayla lagel  
import java.util.stream.Collectors;  
// 📌 Stream API cha use karto je collection transform karayla madat karto  

import org.springframework.beans.factory.annotation.Autowired;
// 📌 Dependency Injection sathi use kela jato  
import org.springframework.stereotype.Service;
// 📌 Service layer chi identification honya sathi  
import org.springframework.transaction.annotation.Transactional;  
// 📌 Database madhye multiple operations ekach transaction madhe handle karayla  

import com.sunbeam.booking.dto.BookingDTO;
// 📌 Booking chi DTO class import keli  
import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.dto.SeatDTO;
// 📌 Bus chi DTO class import keli  
import com.sunbeam.booking.entity.Bus;
// 📌 Bus entity import keli jo database madhye store hoto  
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
// 📌 Custom exception import kela jar record na sapdla tar  
import com.sunbeam.booking.repository.BookingRepository;
// 📌 BookingRepository madhun database operations karto  
import com.sunbeam.booking.repository.BusRepository;  
// 📌 BusRepository madhun database madhye bus related operations karto  

import lombok.extern.slf4j.Slf4j;  
// 📌 Logger use karnya sathi  

@Service  
// 📌 ha annotation sangto ki ha class ek Service layer madhla component ahe  

@Slf4j  
// 📌 ha annotation logger integrate karto jo logs print karaychya astil  

public class BusServiceImpl implements BusService {  
// 📌 ha class BusService cha implementation ahe  

    @Autowired  
    private BusRepository busRepository;  
    // 📌 Bus repository inject keli jo CRUD operations handle karto  

    @Autowired  
    private BookingRepository bookingRepository;  
    // 📌 Booking repository inject keli  

    @Autowired  
    private FareCalculationService fareCalculationService;  
    // 📌 Fare calculation service inject keli jo ticket fare calculate karayla madat karto  

    /**
     * ✅ Saglya buses fetch karun DTO madhe convert karto.
     */
    @Override  
    public List<BusDTO> findAll() {  
        return busRepository.findAll().stream()  
                .map(this::convertToBusDTO)  
                .collect(Collectors.toList());  
        // 📌 Saglya buses fetch karun DTO madhe convert karto ani return karto  
    }  

    /**
     * ✅ Bus ID ne ek specific bus gheto.
     * - Jar bus nahi sapdli tar `ResourceNotFoundException` throw karto.
     */
//    @Override  
//    public BusDTO findBusDTOById(Long id) {  
//        return busRepository.findById(id)  
//            .map(DTOMapper::toBusDTO)  
//            .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));  
//        // 📌 Bus fetch karto ani DTO madhe convert karto, nahi sapdli tar exception throw karto  
//    }
    
    @Override
    public BusDTO findBusDTOById(Long id) {
        Bus bus = busRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));

        return new BusDTO(bus); // Ensure BusDTO has a constructor that accepts a Bus object.
    }

    
//    @Override
//    public BusDTO findBusDTOById(Long id) {
//        Bus bus = busRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));
//
//        return new BusDTO(
//            bus.getId(),
//            bus.getName(),
//            bus.getSource(),
//            bus.getDestination(),
//            bus.getDepartureTime().toString(),
//            bus.getArrivalTime().toString(),
//            bus.getBusNumber(),
//            bus.getRoute(),
//            bus.getCapacity(),
//            bus.getTotalSeats(),
//            bus.getAvailableSeats(),
//            bus.getFare()
//        );
//    }


    /**
     * ✅ Naveen bus save karaychi kiva existing bus update karaychi.
     */
    @Override  
    @Transactional  
    public BusDTO save(BusDTO busDTO) {  
        Bus bus = convertToBus(busDTO);  
        // 📌 BusDTO la entity madhe convert karto  

        busRepository.save(bus);  
        // 📌 Bus database madhe save karto  

        log.info("✅ Bus saved/updated: {}", bus.getId());  
        // 📌 Log madhye save/update message print karto  

        return convertToBusDTO(bus);  
        // 📌 DTO format madhe saved bus return karto  
    }  

    /**
     * ✅ Bus delete karaychi ahe ID ne.
     * - Jar bus exist nasel tar exception throw karto.
     */
    @Override  
    @Transactional  
    public void deleteById(Long id) {  
        Bus bus = busRepository.findById(id)  
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + id));  
        // 📌 Bus fetch karto, nahi sapdli tar exception throw karto  

        busRepository.delete(bus);  
        // 📌 Bus database madhun delete karto  

        log.info("❌ Bus deleted: ID {}", id);  
        // 📌 Log madhye delete message print karto  
    }  

    /**
     * ✅ Saglya buses sathi bookings fetch karaychya ahet.
     * - Stream API cha use karto je collection transform karto.
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
                        "CONFIRMED" // ✅ Missing status add kelay  
                	)  
                )  
                .collect(Collectors.toList());  
        // 📌 Saglya bookings fetch karun DTO madhe convert karto  
    }  

    /**
     * ✅ Bus route filter karaycha ahe prefix ne.
     * - Case-insensitive filtering karto.
     */
    @Override  
    public List<BusDTO> searchBusRoutesByPrefix(String prefix) {  
        return busRepository.findByRouteStartingWith(prefix).stream()  
                .map(this::convertToBusDTO)  
                .collect(Collectors.toList());  
        // 📌 Prefix ne bus route filter karto ani DTO madhe convert karto  
    }  

    /**
     * ✅ Source ani destination varun fare calculate karto.
     */
    @Override  
    public double calculateFare(String source, String destination) {  
        return fareCalculationService.calculateFare(source, destination);  
        // 📌 Source & destination varun fare calculate karto  
    }  

    /**
     * ✅ Bus entity la BusDTO madhe convert karaychi method.
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
        // 📌 Bus entity madhun DTO madhe convert karto  
    }  

    /**
     * ✅ BusDTO la Bus entity madhe convert karaychi method.
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
        // 📌 DTO madhun entity madhe convert karto  
    }  
    
    public List<BusDTO> searchBuses(String source, String destination, String date) {
        List<Bus> buses = busRepository.findBySourceAndDestination(source, destination);
        
        // Optional: Filter buses by date if required
        return buses.stream()
            .map(bus -> new BusDTO(bus))
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<SeatDTO> getSeatsByBusId(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with ID: " + busId));
        
        return bus.getSeats().stream()
                .map(seat -> new SeatDTO(seat.getId(), seat.getNumber(), seat.isBooked()))
                .collect(Collectors.toList());
    }


}  
