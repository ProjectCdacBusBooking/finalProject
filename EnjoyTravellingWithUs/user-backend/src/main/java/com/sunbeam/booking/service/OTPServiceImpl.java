package com.sunbeam.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OTPServiceImpl implements OTPService {

    private static final Logger log = LoggerFactory.getLogger(OTPServiceImpl.class);
    private static final int OTP_EXPIRATION_MINUTES = 5; // ✅ OTP expires in 5 minutes
    private static final int OTP_LENGTH = 6; // ✅ OTP length

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private EmailNotificationService emailNotificationService;

    /**
     * ✅ Generates a 6-digit OTP, stores it, and sends it via email.
     * - Automatically removes the OTP after expiration.
     */
    @Override
    public String generateOTP(String email) {
        String otp = generateRandomOTP();
        otpStorage.put(email, otp);
        log.info("🔢 OTP generated for {}: {}", email, otp);

        emailNotificationService.sendOTP(email, otp);

        // ✅ Schedule OTP removal after expiration time
        scheduler.schedule(() -> {
            otpStorage.remove(email);
            log.info("⏳ OTP expired for {}", email);
        }, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);

        return otp;
    }

    /**
     * ✅ Validates OTP and removes it after successful verification.
     */
    @Override
    public boolean validateOTP(String email, String otp) {
        log.info("🔍 Validating OTP for {}", email);
        
        if (otpStorage.containsKey(email) && otpStorage.get(email).equals(otp)) {
            otpStorage.remove(email); // ✅ Remove OTP after successful validation
            log.info("✅ OTP verified successfully for {}", email);
            return true;
        }

        log.warn("❌ OTP validation failed for {}", email);
        return false;
    }

    /**
     * ✅ Generates a secure random OTP of specified length.
     */
    private String generateRandomOTP() {
        return String.format("%0" + OTP_LENGTH + "d", random.nextInt((int) Math.pow(10, OTP_LENGTH)));
    }
}
