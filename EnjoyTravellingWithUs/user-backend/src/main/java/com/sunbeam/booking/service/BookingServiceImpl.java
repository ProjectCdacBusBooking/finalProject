package com.sunbeam.booking.service;  
// 📌 he package declaration ahe, jo sangto ki ha service layer cha part ahe  

import java.time.format.DateTimeFormatter;  
// 📌 Date format karayla formatter import kelay  

import java.util.List;  
// 📌 List import keli jyane multiple bookings return karata yetil  

import java.util.concurrent.locks.ReentrantLock;  
// 📌 ReentrantLock ha multi-threading safety sathi use hoto  

import java.util.stream.Collectors;  
// 📌 Stream API cha use karto jo collection transform karayla madat karto  

import org.springframework.beans.factory.annotation.Autowired;  
// 📌 Dependency Injection sathi use kela jato  

import org.springframework.stereotype.Service;  
// 📌 Service layer chi identification honya sathi  

import org.springframework.transaction.annotation.Transactional;  
// 📌 Database madhye multiple operations ekach transaction madhe handle karayla  

import com.sunbeam.booking.dto.BookingDTO;  
// 📌 Booking chi DTO class import keli  

import com.sunbeam.booking.entity.Booking;  
// 📌 Booking entity import keli jo database madhye store hoto  

import com.sunbeam.booking.entity.Bus;  
// 📌 Bus entity import keli  

import com.sunbeam.booking.entity.User;  
// 📌 User entity import keli  

import com.sunbeam.booking.exceptions.ResourceNotFoundException;  
// 📌 Custom exception import kelay jar record na sapdla tar  

import com.sunbeam.booking.repository.BookingRepository;  
// 📌 BookingRepository madhun database operations karto  

import com.sunbeam.booking.repository.BusRepository;  
// 📌 BusRepository madhun database madhye bus related operations karto  

import com.sunbeam.booking.repository.UserRepository;  
// 📌 UserRepository madhun database madhye user related operations karto  

import com.sunbeam.booking.util.DTOMapper;  
// 📌 DTOMapper import karto je entity la DTO madhe convert karto  

import lombok.extern.slf4j.Slf4j;  
// 📌 Logger use karnya sathi  

@Service  
// 📌 ha annotation sangto ki ha class ek Service layer madhla component ahe  

@Slf4j  
// 📌 ha annotation logger integrate karto jeva logs print karaychya astil  

public class BookingServiceImpl implements BookingService {  
// 📌 ha class BookingService cha implementation ahe  

    private final ReentrantLock lock = new ReentrantLock(); // 🔐 Ensures thread safety  
    // 📌 ha lock multiple threads ekach vela same booking create karu naye mhanun lavlay  

    @Autowired  
    private BookingRepository bookingRepository;  
    // 📌 Booking repository inject keli jo CRUD operations handle karto  

    @Autowired  
    private BusRepository busRepository;  
    // 📌 Bus repository inject keli  

    @Autowired  
    private UserRepository userRepository;  
    // 📌 User repository inject keli  

	@Override  
	@Transactional  
	public BookingDTO createBooking(BookingDTO bookingDTO) {  
    // 📌 Navin booking create karaychi ahe  

    lock.lock();  // ✅ Thread safety maintain karto  
    try {  
        User user = userRepository.findById(bookingDTO.getUserId())  
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));  
        // 📌 User ID ne user fetch karto, jar nahi sapdla tar exception throw karto  

        Bus bus = busRepository.findById(bookingDTO.getBusId())  
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));  
        // 📌 Bus ID ne bus fetch karto, jar nahi sapdli tar exception throw karto  

        Booking booking = new Booking();  
        booking.setUser(user);  
        booking.setBus(bus);  
        booking.setBookingDate(bookingDTO.getBookingDate()); // ✅ Now uses parsed LocalDateTime  
        // 📌 Booking date set karto  

        booking.setSeatNumber(bookingDTO.getSeatNumber());  
        // 📌 Seat number set karto  

        booking.setPrice(bookingDTO.getPrice());  
        // 📌 Booking price set karto  

        booking.setStatus("CONFIRMED");  
        // 📌 Booking status 'CONFIRMED' set karto  

        bookingRepository.save(booking);  
        // 📌 Booking database madhye save karto  

        log.info("✅ Booking created: User {} | Bus {} | Seat {}",   
                 bookingDTO.getUserId(), bookingDTO.getBusId(), bookingDTO.getSeatNumber());  
        // 📌 Log statement jo booking details print karto  

        return bookingDTO;  
    } finally {  
        lock.unlock();  // ✅ Lock release karto  
    }  
}  

    @Override  
    @Transactional  
    public boolean cancelBooking(Long bookingId) {  
        // 📌 Booking cancel karaychi ahe  

        Booking booking = bookingRepository.findById(bookingId)  
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));  
        // 📌 Booking fetch karto, jar nahi sapdli tar exception throw karto  

        bookingRepository.delete(booking);  
        // 📌 Booking database madhun delete karto  

        log.info("❌ Booking cancelled: ID {}", bookingId);  
        // 📌 Log madhye cancellation message print karto  

        return true;  
    }  

    @Override  
    public BookingDTO getBookingById(Long bookingId) {  
        // 📌 Particular booking fetch karaychi ahe ID varun  

        return bookingRepository.findById(bookingId)  
            .map(DTOMapper::toBookingDTO)  
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));  
        // 📌 Booking ID sathi DTO madhe mapping karto nahi tar exception throw karto  
    }  

    @Override  
    public List<BookingDTO> getAllBookings() {  
        // 📌 Saglya bookings chi list return karto  

        return bookingRepository.findAll().stream()  
                .map(this::convertToBookingDTO)  
                .collect(Collectors.toList());  
        // 📌 Saglya bookings stream madhun DTO madhe convert karto  
    }  

    @Override  
    public List<BookingDTO> getBookingsByUser(Long userId) {  
        // 📌 Specific user cha bookings return karto  

        return bookingRepository.findByUserId(userId).stream()  
                .map(this::convertToBookingDTO)  
                .collect(Collectors.toList());  
        // 📌 User ID varun bookings find karun DTO madhe convert karto  
    }  

    @Override  
    @Transactional  
    public boolean updateBooking(Long bookingId, BookingDTO bookingDTO) {  
        // 📌 Booking update karaychi ahe  

        Booking booking = bookingRepository.findById(bookingId)  
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));  
        // 📌 Booking fetch karto, nahi sapdli tar exception throw karto  

        booking.setBookingDate(bookingDTO.getBookingDate());  
        // 📌 Booking date update karto  

        booking.setSeatNumber(bookingDTO.getSeatNumber());  
        // 📌 Seat number update karto  

        booking.setPrice(bookingDTO.getPrice());  
        // 📌 Price update karto  

        booking.setStatus(bookingDTO.getStatus()); // ✅ Ensure status updates correctly  
        // 📌 Booking status update karto  

        bookingRepository.save(booking);  
        // 📌 Database madhye updated booking save karto  

        log.info("🔄 Booking updated: ID {}", bookingId);  
        // 📌 Log madhye booking update message print karto  

        return true;  
    }  

    private BookingDTO convertToBookingDTO(Booking booking) {  
        // 📌 Booking entity la DTO madhe convert karaychi method  

        return new BookingDTO(  
            booking.getId(),  
            booking.getUser().getId(),  
            booking.getBus().getId(),  
            booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), // ✅ Fix here  
            // 📌 Date format correct karto JSON response sathi  

            booking.getSeatNumber(),  
            booking.getPrice(),  
            booking.getStatus()  
        );  
    }  

}  
