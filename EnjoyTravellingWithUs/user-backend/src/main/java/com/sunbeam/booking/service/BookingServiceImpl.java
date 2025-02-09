package com.sunbeam.booking.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.entity.Booking;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.BookingRepository;
import com.sunbeam.booking.repository.BusRepository;
import com.sunbeam.booking.repository.UserRepository;
import com.sunbeam.booking.util.DTOMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final ReentrantLock lock = new ReentrantLock(); // 🔐 Ensures thread safety

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

	@Override
	@Transactional
	public BookingDTO createBooking(BookingDTO bookingDTO) {
    lock.lock();
    try {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Bus bus = busRepository.findById(bookingDTO.getBusId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setBookingDate(bookingDTO.getBookingDate()); // ✅ Now uses parsed LocalDateTime
        booking.setSeatNumber(bookingDTO.getSeatNumber());
        booking.setPrice(bookingDTO.getPrice());
        booking.setStatus("CONFIRMED");

        bookingRepository.save(booking);

        log.info("✅ Booking created: User {} | Bus {} | Seat {}", 
                 bookingDTO.getUserId(), bookingDTO.getBusId(), bookingDTO.getSeatNumber());

        return bookingDTO;
    } finally {
        lock.unlock();
    }
}


    @Override
    @Transactional
    public boolean cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        bookingRepository.delete(booking);
        log.info("❌ Booking cancelled: ID {}", bookingId);
        return true;
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
            .map(DTOMapper::toBookingDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }


    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToBookingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::convertToBookingDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean updateBooking(Long bookingId, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        //booking.setBookingDate(LocalDateTime.parse(bookingDTO.getBookingDate(), DateTimeFormatter.ISO_DATE_TIME)); // ✅ Correct parsing

        booking.setBookingDate(bookingDTO.getBookingDate());
        
        booking.setSeatNumber(bookingDTO.getSeatNumber());
        booking.setPrice(bookingDTO.getPrice());
        booking.setStatus(bookingDTO.getStatus()); // ✅ Ensure status updates correctly

        bookingRepository.save(booking);
        log.info("🔄 Booking updated: ID {}", bookingId);
        return true;
    }

    private BookingDTO convertToBookingDTO(Booking booking) {
        return new BookingDTO(
            booking.getId(),
            booking.getUser().getId(),
            booking.getBus().getId(),
            booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), // ✅ Fix here
            booking.getSeatNumber(),
            booking.getPrice(),
            booking.getStatus()
        );
    }

}
