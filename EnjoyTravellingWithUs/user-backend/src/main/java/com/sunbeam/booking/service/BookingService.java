package com.sunbeam.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.entity.Booking;
import com.sunbeam.booking.repository.BookingRepository;
import com.sunbeam.booking.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setUser(userRepository.findById(bookingDTO.getUserId()).orElse(null));
        booking.setBusId(bookingDTO.getBusId());
        booking.setSeatNumber(bookingDTO.getSeatNumber());
        booking.setBookingDate(new java.util.Date()); // Set current date for booking
        booking.setBookingStatus("CONFIRMED"); // Default status CONFIRMED
        Booking savedBooking = bookingRepository.save(booking);

        return new BookingDTO(
            savedBooking.getId(),
            savedBooking.getUser().getId(),
            savedBooking.getBusId(),
            savedBooking.getSeatNumber(),
            savedBooking.getBookingDate(),
            savedBooking.getBookingStatus()
        );
    }


    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> return new BookingDTO(booking.getId(), booking.getUser().getId(), booking.getBus().getId(), 
                        booking.getSeatNumber(), booking.getBookingDate(), booking.getBookingStatus())

                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(b -> new BookingDTO(
                        b.getId(),
                        b.getUser().getId(),
                        b.getBusId(),
                        b.getSeatNumber(),
                        b.getBookingDate().toString(),
                        b.getBookingStatus()))
                      .orElse(null);
    }
}
