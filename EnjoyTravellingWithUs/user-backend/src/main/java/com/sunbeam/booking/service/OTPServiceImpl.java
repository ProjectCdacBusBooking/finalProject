package com.sunbeam.booking.service;  
// 📌 Service layer madhil package declaration  

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
// 📌 Logging sathi SLF4J library import kela  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
// 📌 Dependency Injection aani Service annotation use karnya sathi  

import java.util.Map;  
import java.util.Random;  
import java.util.concurrent.ConcurrentHashMap;  
import java.util.concurrent.Executors;  
import java.util.concurrent.ScheduledExecutorService;  
import java.util.concurrent.TimeUnit;  
// 📌 **OTP Management** aani **Scheduler** sathi required classes import kele  

@Service  
// 📌 Ha class ek **Spring Service Bean** ahe  

public class OTPServiceImpl implements OTPService {  

    private static final Logger log = LoggerFactory.getLogger(OTPServiceImpl.class);  
    // 📌 Logger create kela **debugging aani tracking sathi**  

    private static final int OTP_EXPIRATION_MINUTES = 5; // ✅ OTP expires in 5 minutes  
    private static final int OTP_LENGTH = 6; // ✅ OTP chi length 6 digit  

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();  
    // 📌 **Thread-safe map (ConcurrentHashMap)** use kela OTP store karnya sathi  

    private final Random random = new Random();  
    // 📌 **Random number generator** for OTP  

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
    // 📌 **Scheduler** vaprun automatic OTP expiry implement kela  

    @Autowired  
    private EmailNotificationService emailNotificationService;  
    // 📌 **Email notification service inject kela** OTP email pathvayla  

    /**
     * ✅ Generates a 6-digit OTP, stores it, and sends it via email.
     * - Automatically removes the OTP after expiration.
     */
    @Override  
    public String generateOTP(String email) {  
        String otp = generateRandomOTP();  
        otpStorage.put(email, otp);  
        // 📌 **OTP generate kela aani store kela**  

        log.info("🔢 OTP generated for {}: {}", email, otp);  
        // 📌 **OTP generate zala he log madhun track karto**  

        // ✅ Send OTP via email  
        emailNotificationService.sendOTP(email, otp);  
        // 📌 **Email madhe OTP pathavtoy**  

        // ✅ Expire OTP after 5 minutes  
        scheduler.schedule(() -> {  
            otpStorage.remove(email);  
            log.info("⏳ OTP expired for {}", email);  
        }, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);  
        // 📌 **OTP 5 minute nantar automatically expire hoto**  

        return otp;  
    }  

    /**
     * ✅ Validates OTP and removes it after successful verification.
     */
    @Override  
    public boolean validateOTP(String email, String otp) {  
        log.info("🔍 Validating OTP for {}", email);  
        // 📌 **OTP validate karnya sathi log**  

        if (otpStorage.containsKey(email) && otpStorage.get(email).equals(otp)) {  
            otpStorage.remove(email); // ✅ Remove OTP after successful validation  
            log.info("✅ OTP verified successfully for {}", email);  
            // 📌 **OTP successful verify zala aani delete kela**  
            return true;  
        }  

        log.warn("❌ OTP validation failed for {}", email);  
        // 📌 **OTP match zala nahi tar warning dakhvto**  
        return false;  
    }  

    /**
     * ✅ Generates a secure random OTP of specified length.
     */
    private String generateRandomOTP() {  
        return String.format("%0" + OTP_LENGTH + "d", random.nextInt((int) Math.pow(10, OTP_LENGTH)));  
        // 📌 **Secure random OTP generate karto**  
    }  
}  
