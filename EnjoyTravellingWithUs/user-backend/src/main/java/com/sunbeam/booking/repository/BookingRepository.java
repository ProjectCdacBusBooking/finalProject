package com.sunbeam.booking.repository;  
// 📌 he package declaration ahe, jo sangto ki ha repository "com.sunbeam.booking.repository" ya package madhye ahe  

import java.util.List;  
// 📌 List cha import jeva multiple bookings fetch karaychya astil  

import org.springframework.data.jpa.repository.JpaRepository;  
// 📌 JpaRepository import karto jo basic CRUD operations handle karto  

import org.springframework.data.jpa.repository.Query;  
// 📌 ha annotation custom queries likhayla use hoto  

import org.springframework.stereotype.Repository;  
// 📌 ha annotation sangto ki ha interface ek Repository ahe (Spring la olkhyasathi)  

import com.sunbeam.booking.entity.Booking;  
// 📌 Booking entity la import karto jeva tya var CRUD operations perform karayche astil  

@Repository  
// 📌 ha annotation Spring container la sangto ki ha repository ahe jeva database operations karayche astil  

public interface BookingRepository extends JpaRepository<Booking, Long> {  
// 📌 JpaRepository<Booking, Long> ha interface Booking entity sathi CRUD operations deto  
// ✅ Booking - entity chi class  
// ✅ Long - ID cha data type  

    @Query("SELECT b FROM Booking b JOIN FETCH b.user JOIN FETCH b.bus WHERE b.user.id = :userId")  
    // 📌 he custom query ahe jo ek user cha sagle bookings fetch karto  
    // ✅ JOIN FETCH b.user: User chi information pan load hoil  
    // ✅ JOIN FETCH b.bus: Bus chi information pan load hoil  
    // ✅ WHERE b.user.id = :userId: Fakta specific user ID sathi bookings fetch karnar  

    List<Booking> findByUserId(Long userId);  
    // 📌 ha method specific user cha sagle bookings return karto  

    boolean existsByBusIdAndSeatNumber(Long busId, String seatNumber);  
    // 📌 he method check karto ki ek particular bus madhe given seat already booked ahe ka  
    // ✅ true return karnar - jar seat already booked asel tar  
    // ✅ false return karnar - jar seat available asel tar  

}
