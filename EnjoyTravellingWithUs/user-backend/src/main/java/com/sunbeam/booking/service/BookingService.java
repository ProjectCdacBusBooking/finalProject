package com.sunbeam.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.entity.Booking;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.repository.BookingRepository;
import com.sunbeam.booking.repository.BusRepository;
import com.sunbeam.booking.repository.UserRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean createBooking(BookingDTO bookingDTO) {
        Optional<Bus> busOpt = busRepository.findById(bookingDTO.getBusId());
        Optional<User> userOpt = userRepository.findById(bookingDTO.getUserId());

        if (busOpt.isPresent() && userOpt.isPresent()) {
            Booking booking = new Booking();
            booking.setBus(busOpt.get());
            booking.setUser(userOpt.get());
            booking.setBookingDate(bookingDTO.getBookingDate());
            booking.setSeatNumber(bookingDTO.getSeatNumber());
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }
    
    public Booking save(Booking booking) {
    	return bookingRepository.save(booking);
    }

    public List<BookingDTO> getBookingsByUser(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(this::convertToBookingDTO).collect(Collectors.toList());
    }

    public boolean cancelBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isPresent()) {
            bookingRepository.delete(bookingOpt.get());
            return true;
        }
        return false;
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream().map(this::convertToBookingDTO).collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        return booking != null ? convertToBookingDTO(booking) : null;
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public double calculateFare(String source, String destination) {
        // Implement your fare calculation logic here
        return 100.0; // Example fare value
    }

    private BookingDTO convertToBookingDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getUser().getId(),
                booking.getBus().getId(),
                booking.getBookingDate(),
                booking.getSeatNumber()
        );
    }
}
