package com.sunbeam.booking.service;  
// 📌 Service layer madhil package declaration  

import org.springframework.stereotype.Service;  
// 📌 Service annotation, jo ha class ek Spring service ahe te indicate karto  

import lombok.extern.slf4j.Slf4j;  
// 📌 Logger integration sathi, je logs maintain karayla madat karto  

@Service  
// 📌 Ha class ek service bean ahe je Spring container madhye manage hoil  

@Slf4j  
// 📌 Logging sathi use hoil jo execution details track karto  

public class DynamicPricingServiceImpl implements DynamicPricingService {  
// 📌 DynamicPricingService cha implementation define kelay  

    // ✅ Peak hours & multipliers define kele je pricing adjust karayla madat kartil  

    private static final int MORNING_PEAK_START = 7;  
    // 📌 Sakali peak hours suru honar 7 AM la  

    private static final int MORNING_PEAK_END = 9;  
    // 📌 Sakali peak hours samplil 9 AM la  

    private static final double MORNING_MULTIPLIER = 1.2;  
    // 📌 Sakali peak hours madhe 20% fare increment hoil  

    private static final int EVENING_PEAK_START = 17;  
    // 📌 Sandhyakalcha peak hours start honar 5 PM la  

    private static final int EVENING_PEAK_END = 19;  
    // 📌 Sandhyakalcha peak hours sampnar 7 PM la  

    private static final double EVENING_MULTIPLIER = 1.5;  
    // 📌 Sandhyakalcha peak hours madhe 50% fare increment hoil  

    private static final double DEFAULT_MULTIPLIER = 1.0;  
    // 📌 Normal pricing sathi je fare original rahil  

    /**
     * ✅ Peak hours nusar dynamic price calculate karaychi method.
     * - Sakali 7 AM - 9 AM & Sandhyakal 5 PM - 7 PM fare la increment karaycha ahe.
     * - Multipliers predefine kele ki easily configure karu shakto.
     */
    @Override  
    public double calculateDynamicPrice(double basePrice, int hourOfDay) {  
        double finalPrice;  
        // 📌 Final price store karnya sathi variable  

        if (hourOfDay >= MORNING_PEAK_START && hourOfDay <= MORNING_PEAK_END) {  
            // 📌 Sakali 7-9 AM madhe price increment hoil  
            finalPrice = basePrice * MORNING_MULTIPLIER;  
            log.info("🌅 Morning Peak Pricing Applied: {} -> {}", basePrice, finalPrice);  
            // 📌 Sakali peak hour fare la increment kela ani log madhye print kela  

        } else if (hourOfDay >= EVENING_PEAK_START && hourOfDay <= EVENING_PEAK_END) {  
            // 📌 Sandhyakal 5-7 PM madhe price increment hoil  
            finalPrice = basePrice * EVENING_MULTIPLIER;  
            log.info("🌆 Evening Peak Pricing Applied: {} -> {}", basePrice, finalPrice);  
            // 📌 Sandhyakal peak hour fare la increment kela ani log madhye print kela  

        } else {  
            // 📌 Jar normal time madhe asel tar default price lagel  
            finalPrice = basePrice * DEFAULT_MULTIPLIER;  
            log.info("🕰️ Regular Pricing Applied: {}", basePrice);  
            // 📌 Normal pricing maintain keli ani log madhe store kela  
        }  

        return finalPrice;  
        // 📌 Final price return karto jo user la charge hoil  
    }  
}  
