package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;

@Service
public class DynamicPricingService {
    // Define peak hours (example: 8 AM - 10 AM and 5 PM - 7 PM)
    private static final int PEAK_HOUR_START_MORNING = 8;
    private static final int PEAK_HOUR_END_MORNING = 10;
    private static final int PEAK_HOUR_START_EVENING = 17;
    private static final int PEAK_HOUR_END_EVENING = 19;

    public double calculateDynamicPrice(double basePrice, int hourOfDay) {
        if ((hourOfDay >= PEAK_HOUR_START_MORNING && hourOfDay < PEAK_HOUR_END_MORNING) ||
            (hourOfDay >= PEAK_HOUR_START_EVENING && hourOfDay < PEAK_HOUR_END_EVENING)) {
            return basePrice * 1.5; // 50% increase during peak hours
        } else {
            return basePrice;
        }
    }

    
}
