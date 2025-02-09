package com.sunbeam.booking.service;

public interface EmailNotificationService {
    void sendEmail(String toEmail, String subject, String body);
    void sendOTP(String toEmail, String otp);
    void sendBookingConfirmation(String toEmail, String busDetails, String bookingId);
    void sendBookingCancellation(String toEmail, String cancellationDetails);
    void sendArrivalReminder(String toEmail, String busDetails);
    void sendReviewRequest(String toEmail, String reviewDetails);
}
