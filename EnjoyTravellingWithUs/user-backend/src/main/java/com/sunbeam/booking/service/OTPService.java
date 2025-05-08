package com.sunbeam.booking.service;

public interface OTPService {
    String generateOTP(String email);
    boolean validateOTP(String email, String otp);
}
