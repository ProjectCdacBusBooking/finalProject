package com.sunbeam.booking.controller;

import com.sunbeam.booking.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailNotificationService emailService;

    @GetMapping("/send")
    public String sendTestEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "📧 Email Sent Successfully!";
    }

    @GetMapping("/send-otp")
    public String sendOTP(@RequestParam String to, @RequestParam String otp) {
        emailService.sendOTP(to, otp);
        return "🔐 OTP Sent Successfully!";
    }

    @GetMapping("/booking-confirmation")
    public String sendBookingConfirmation(@RequestParam String to, @RequestParam String busDetails, @RequestParam String bookingId) {
        emailService.sendBookingConfirmation(to, busDetails, bookingId);
        return "🚌 Booking Confirmation Sent!";
    }

    @GetMapping("/booking-cancellation")
    public String sendBookingCancellation(@RequestParam String to, @RequestParam String cancellationDetails) {
        emailService.sendBookingCancellation(to, cancellationDetails);
        return "❌ Booking Cancellation Sent!";
    }

    @GetMapping("/arrival-reminder")
    public String sendArrivalReminder(@RequestParam String to, @RequestParam String busDetails) {
        emailService.sendArrivalReminder(to, busDetails);
        return "🚏 Arrival Reminder Sent!";
    }

    @GetMapping("/review-request")
    public String sendReviewRequest(@RequestParam String to, @RequestParam String reviewDetails) {
        emailService.sendReviewRequest(to, reviewDetails);
        return "⭐ Review Request Sent!";
    }
}
