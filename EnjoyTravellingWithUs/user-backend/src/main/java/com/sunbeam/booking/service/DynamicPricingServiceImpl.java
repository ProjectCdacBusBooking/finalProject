package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DynamicPricingServiceImpl implements DynamicPricingService {

    // ✅ Constants for peak hours & multipliers
    private static final int MORNING_PEAK_START = 7;
    private static final int MORNING_PEAK_END = 9;
    private static final double MORNING_MULTIPLIER = 1.2;

    private static final int EVENING_PEAK_START = 17;
    private static final int EVENING_PEAK_END = 19;
    private static final double EVENING_MULTIPLIER = 1.5;

    private static final double DEFAULT_MULTIPLIER = 1.0; // ✅ Regular price multiplier

    /**
     * ✅ Calculates dynamic pricing based on peak hours.
     * - Increases price during morning (7 AM - 9 AM) and evening (5 PM - 7 PM).
     * - Uses predefined multipliers for easier configuration.
     */
    @Override
    public double calculateDynamicPrice(double basePrice, int hourOfDay) {
        double finalPrice;

        if (hourOfDay >= MORNING_PEAK_START && hourOfDay <= MORNING_PEAK_END) {
            finalPrice = basePrice * MORNING_MULTIPLIER; // ✅ Morning peak pricing
            log.info("🌅 Morning Peak Pricing Applied: {} -> {}", basePrice, finalPrice);
        } else if (hourOfDay >= EVENING_PEAK_START && hourOfDay <= EVENING_PEAK_END) {
            finalPrice = basePrice * EVENING_MULTIPLIER; // ✅ Evening peak pricing
            log.info("🌆 Evening Peak Pricing Applied: {} -> {}", basePrice, finalPrice);
        } else {
            finalPrice = basePrice * DEFAULT_MULTIPLIER; // ✅ Regular price outside peak hours
            log.info("🕰️ Regular Pricing Applied: {}", basePrice);
        }

        return finalPrice;
    }
}
