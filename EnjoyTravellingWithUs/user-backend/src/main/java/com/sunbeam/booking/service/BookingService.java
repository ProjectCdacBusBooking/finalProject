package com.sunbeam.booking.service;

import java.util.List;
import com.sunbeam.booking.dto.BookingDTO;

public interface BookingService {

    /**
     * ✅ Creates a new booking.
     * - Returns the created `BookingDTO` on success.
     */
    BookingDTO createBooking(BookingDTO bookingDTO);

    /**
     * ✅ Cancels an existing booking by ID.
     * - Returns `true` if cancellation is successful, `false` otherwise.
     */
    boolean cancelBooking(Long id);

    /**
     * ✅ Retrieves all bookings for a specific user.
     */
    List<BookingDTO> getBookingsByUser(Long userId);

    /**
     * ✅ Retrieves a booking by ID.
     */
    BookingDTO getBookingById(Long id);

    /**
     * ✅ Retrieves all bookings in the system.
     */
    List<BookingDTO> getAllBookings();

    /**
     * ✅ Updates a booking by ID.
     * - Returns `true` if update is successful, `false` otherwise.
     */
    boolean updateBooking(Long bookingId, BookingDTO bookingDTO);
}
