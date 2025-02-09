package com.sunbeam.booking.service;

public interface DynamicPricingService {
    
    /**
     * ✅ Calculates dynamic fare based on peak hours.
     * - Takes base price and current hour of the day.
     * - Returns adjusted price based on time-based multipliers.
     */
    double calculateDynamicPrice(double basePrice, int hourOfDay);
}
