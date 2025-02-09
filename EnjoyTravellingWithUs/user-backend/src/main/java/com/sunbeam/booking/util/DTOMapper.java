package com.sunbeam.booking.util;

import com.sunbeam.booking.dto.*;
import com.sunbeam.booking.entity.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // 🔹 Convert Booking Entity to BookingDTO
    public static BookingDTO toBookingDTO(Booking booking) {
        return new BookingDTO(
            booking.getId(),
            booking.getUser().getId(),
            booking.getBus().getId(),
            booking.getBookingDate().format(FORMATTER),
            booking.getSeatNumber(),
            booking.getPrice(),
            booking.getStatus()
        );
    }

    // 🔹 Convert Bus Entity to BusDTO
    public static BusDTO toBusDTO(Bus bus) {
        return new BusDTO(
            bus.getId(),
            bus.getName(),
            bus.getSource(),
            bus.getDestination(),
            bus.getDepartureTime().format(FORMATTER),
            bus.getArrivalTime().format(FORMATTER),
            bus.getBusNumber(),
            bus.getRoute(),
            bus.getCapacity(),
            bus.getTotalSeats(),
            bus.getAvailableSeats(),
            bus.getFare()
        );
    }

    // 🔹 Convert User Entity to UserDTO
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getContact(),
            user.getPassword()
        );
    }

    // 🔹 Convert Wallet Entity to WalletDTO
    public static WalletDTO toWalletDTO(Wallet wallet) {
        return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
    }

    // 🔹 Convert List of Bookings to List of BookingDTOs
    public static List<BookingDTO> toBookingDTOList(List<Booking> bookings) {
        return bookings.stream().map(DTOMapper::toBookingDTO).collect(Collectors.toList());
    }

    // 🔹 Convert List of Buses to List of BusDTOs
    public static List<BusDTO> toBusDTOList(List<Bus> buses) {
        return buses.stream().map(DTOMapper::toBusDTO).collect(Collectors.toList());
    }

    // 🔹 Convert List of Users to List of UserDTOs
    public static List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream().map(DTOMapper::toUserDTO).collect(Collectors.toList());
    }
}
