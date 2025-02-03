package com.sunbeam.booking.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.BookingConfirmationRequest;
import com.sunbeam.booking.dto.BookingDTO;
import com.sunbeam.booking.dto.BookingHistoryDTO;
import com.sunbeam.booking.dto.CancelBookingDTO;
import com.sunbeam.booking.dto.SeatSelectionRequest;
import com.sunbeam.booking.entity.Booking;
import com.sunbeam.booking.repository.BookingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor  // ✅ हे Constructor Injection साठी योग्य आहे
public class BookingService {

    private final BookingRepository bookingRepository;  // ✅ Final ठेवले तरी चालेल

    public boolean selectSeats(SeatSelectionRequest request) {
        if (request.getBusId() == 1L && request.getSelectedSeats() > 0) {
            return true;
        }
        return false;
    }
    
    public List<BookingHistoryDTO> getBookingHistory(int userId) {
        List<BookingHistoryDTO> bookingHistory = new ArrayList<>();
        bookingHistory.add(new BookingHistoryDTO(1, "2025-02-01", "1,2,3"));
        bookingHistory.add(new BookingHistoryDTO(2, "2025-02-05", "4,5"));
        return bookingHistory;
    }

    // Mock booking cancellation
    public String cancelBooking(CancelBookingDTO cancelBookingDTO) {
        // In a real app, logic for cancelling the booking would be implemented here.
        return "Booking cancelled successfully!";
    }

    public boolean confirmBooking(BookingConfirmationRequest request) {
        if (request.getBusId() == 1L && request.getSelectedSeats() > 0) {
            return true;
        }
        return false;
    }

    public List<Booking> getBookingHistory(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public boolean cancelBooking(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            if (booking.getBookingStatus().equals("CONFIRMED")) { // ✅ Corrected
                booking.setBookingStatus("CANCELLED"); // ✅ Corrected
                bookingRepository.save(booking);
                return true;
            }
        }
        return false;
    }
    
    public double processRefund(Booking booking) {
        // For simplicity, assume full refund if the booking is cancelled
        return 100.00; // Dummy refund amount
    }

    
//    public Booking cancelBooking(Long bookingId) {
//        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
//        
//        // Update the booking status and set cancellation flag
//        booking.setStatus("cancelled");
//        booking.setCancelled(true);
//        
//        return bookingRepository.save(booking);
//    }
    
    /**
     * ✅ Get Booking Details Method
     * 📌 बुकिंग तपशील मिळवण्यासाठी वापरला जातो.
     */
    public BookingDTO getBookingDetails(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            // DTO conversion can be done here
            return new BookingDTO(booking);
        }
        return null;
    }
}
