package com.sunbeam.booking.controller;

import com.sunbeam.booking.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email") // Email related APIs handle karnyasathi controller
public class EmailController {

    @Autowired
    private EmailNotificationService emailService; // Email notification service inject karto

    @GetMapping("/send")
    public String sendTestEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body); // Email send karayla service method call karto
        return "📧 Email Sent Successfully!"; // Success message return karto
    }

    @GetMapping("/send-otp")
    public String sendOTP(@RequestParam String to, @RequestParam String otp) {
        emailService.sendOTP(to, otp); // OTP send karayla service method call karto
        return "🔐 OTP Sent Successfully!"; // Success message return karto
    }

    @GetMapping("/booking-confirmation")
    public String sendBookingConfirmation(@RequestParam String to, @RequestParam String busDetails, @RequestParam String bookingId) {
        emailService.sendBookingConfirmation(to, busDetails, bookingId); // Booking confirmation send karto
        return "🚌 Booking Confirmation Sent!"; // Success message return karto
    }

    @GetMapping("/booking-cancellation")
    public String sendBookingCancellation(@RequestParam String to, @RequestParam String cancellationDetails) {
        emailService.sendBookingCancellation(to, cancellationDetails); // Booking cancellation email send karto
        return "❌ Booking Cancellation Sent!"; // Success message return karto
    }

    @GetMapping("/arrival-reminder")
    public String sendArrivalReminder(@RequestParam String to, @RequestParam String busDetails) {
        emailService.sendArrivalReminder(to, busDetails); // Arrival reminder send karto
        return "🚏 Arrival Reminder Sent!"; // Success message return karto
    }

    @GetMapping("/review-request")
    public String sendReviewRequest(@RequestParam String to, @RequestParam String reviewDetails) {
        emailService.sendReviewRequest(to, reviewDetails); // Review request email send karto
        return "⭐ Review Request Sent!"; // Success message return karto
    }
}
