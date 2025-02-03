package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPService {

    public String generateOTP() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000)); // Generate 4-digit OTP
    }

    public boolean validateOTP(String inputOTP, String generatedOTP) {
        return inputOTP.equals(generatedOTP);
    }
}
