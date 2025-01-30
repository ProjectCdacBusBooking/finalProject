package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;

import com.sunbeam.booking.repository.BusRepository;

import lombok.RequiredArgsConstructor;

/**
 * 📝 BusService - Bus संबंधित सर्व Service Methods
 * 📌 Bus Search, Bus Details, Seat Availability, Booking प्रबंधन संबंधित सर्व ऑपरेशन्स.
 */
@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    /**
     * ✅ Get Bus Details Method
     * 📌 Bus ID नुसार बसा ची तपशील माहिती मिळवतो.
     */
    public String getBusDetails(Long busId) {
        // Placeholder for now. Actual DB Logic Required.
        if (busId == 1L) {
            return "Bus1 - Mumbai to Pune - 30 seats available, Departs at 10:00 AM";
        } else if (busId == 2L) {
            return "Bus2 - Mumbai to Pune - 20 seats available, Departs at 2:00 PM";
        }
        return null;
    }
}
