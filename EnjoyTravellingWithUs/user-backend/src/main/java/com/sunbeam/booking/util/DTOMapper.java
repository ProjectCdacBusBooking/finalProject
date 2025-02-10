package com.sunbeam.booking.util;

import com.sunbeam.booking.dto.*;
import com.sunbeam.booking.entity.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {
    
    // 📌 Date format je yyyy-MM-dd'T'HH:mm:ss ya format madhe date store karto.
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * ✅ Booking Entity la BookingDTO madhe convert karanyasathi function.
     * - Entity cha data DTO madhe assign karto.
     */
    public static BookingDTO toBookingDTO(Booking booking) {
        return new BookingDTO(
            booking.getId(), // Booking ID set karto
            booking.getUser().getId(), // User ID gheto
            booking.getBus().getId(), // Bus ID gheto
            booking.getBookingDate().format(FORMATTER), // Booking date format madhe convert karto
            booking.getSeatNumber(), // Seat number assign karto
            booking.getPrice(), // Booking price gheto
            booking.getStatus() // Booking status assign karto
        );
    }

    /**
     * ✅ Bus Entity la BusDTO madhe convert karanyasathi function.
     * - Bus chi sagli mahiti DTO madhe map karto.
     */
    public static BusDTO toBusDTO(Bus bus) {
        return new BusDTO(
            bus.getId(), // Bus ID set karto
            bus.getName(), // Bus name assign karto
            bus.getSource(), // Source city assign karto
            bus.getDestination(), // Destination city assign karto
            bus.getDepartureTime().format(FORMATTER), // Departure time format madhe convert karto
            bus.getArrivalTime().format(FORMATTER), // Arrival time format madhe convert karto
            bus.getBusNumber(), // Bus number assign karto
            bus.getRoute(), // Route assign karto
            bus.getCapacity(), // Capacity assign karto
            bus.getTotalSeats(), // Total seats assign karto
            bus.getAvailableSeats(), // Available seats assign karto
            bus.getFare() // Fare assign karto
        );
    }

    /**
     * ✅ User Entity la UserDTO madhe convert karanyasathi function.
     * - User cha data safely map karto.
     */
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
            user.getId(), // User ID assign karto
            user.getFirstName(), // First name assign karto
            user.getLastName(), // Last name assign karto
            user.getEmail(), // Email assign karto
            user.getContact(), // Contact assign karto
            user.getPassword() // Password assign karto
        );
    }

    /**
     * ✅ Wallet Entity la WalletDTO madhe convert karanyasathi function.
     * - Wallet madhe user chi balance store hote.
     */
    public static WalletDTO toWalletDTO(Wallet wallet) {
        return new WalletDTO(wallet.getUser().getId(), wallet.getBalance()); // User ID and balance assign karto
    }

    /**
     * ✅ Booking list la BookingDTO list madhe convert karto.
     * - Stream API cha use karun list mapping karto.
     */
    public static List<BookingDTO> toBookingDTOList(List<Booking> bookings) {
        return bookings.stream().map(DTOMapper::toBookingDTO).collect(Collectors.toList());
    }

    /**
     * ✅ Bus list la BusDTO list madhe convert karto.
     * - Stream API vaprun efficiently list convert karto.
     */
    public static List<BusDTO> toBusDTOList(List<Bus> buses) {
        return buses.stream().map(DTOMapper::toBusDTO).collect(Collectors.toList());
    }

    /**
     * ✅ User list la UserDTO list madhe convert karto.
     * - List madhe sagle users convert karto DTO madhe.
     */
    public static List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream().map(DTOMapper::toUserDTO).collect(Collectors.toList());
    }
}
