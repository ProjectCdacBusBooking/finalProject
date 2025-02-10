package com.sunbeam.booking.service;  
// 📌 Service layer madhil package declaration  

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
// 📌 SLF4J Logger use karnya sathi  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Value;  
// 📌 Spring Beans inject karnya sathi  

import org.springframework.mail.MailException;  
import org.springframework.mail.javamail.JavaMailSender;  
import org.springframework.mail.javamail.MimeMessageHelper;  
// 📌 Spring mail dependencies  

import org.springframework.stereotype.Service;  
import jakarta.mail.MessagingException;  
import jakarta.mail.internet.MimeMessage;  
// 📌 JavaMail API use karnya sathi  

@Service  
// 📌 Ha class ek service bean ahe jo Spring container madhe manage hoil  

public class EmailNotificationServiceImpl implements EmailNotificationService {  
// 📌 EmailNotificationService interface cha implementation define kela  

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);  
    // 📌 Logger object declare kela  

    @Autowired  
    private JavaMailSender mailSender;  
    // 📌 JavaMailSender dependency inject kela  

    @Value("${spring.mail.username}")  
    private String fromEmail;  
    // 📌 Application properties madhun email sender cha address fetch kela  

    /**
     * ✅ General method to send email.
     * - HTML format support karnya sathi `MimeMessage` vaparlay.
     */
    @Override  
    public void sendEmail(String to, String subject, String body) {  
        try {  
            MimeMessage message = mailSender.createMimeMessage();  
            // 📌 MimeMessage object create kela  

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");  
            // 📌 Helper class use karnya sathi jo attachments & HTML support karto  

            helper.setTo(to);  
            helper.setSubject(subject);  
            helper.setText(body, true); // ✅ HTML format enable kela  
            helper.setFrom(fromEmail);  

            log.info("📧 Sending email to: {}", to);  
            log.info("📧 Subject: {}", subject);  

            mailSender.send(message);  
            // 📌 Email send kela  

            log.info("✅ Email sent successfully!");  

        } catch (MessagingException | MailException e) {  
            log.error("❌ Email failed to send: {}", e.getMessage());  
            throw new RuntimeException("Failed to send email", e);  
        }  
    }  

    /**
     * ✅ Sends a **beautiful** welcome email after user registration.
     */
    @Override  
    public void sendWelcomeEmail(String toEmail, String userName) {  
        String subject = "🎉 Welcome to Enjoy Travelling With Us!";  
        // 📌 Subject define kela  

        String body = """  
            <html>  
            <body style="font-family: Arial, sans-serif; text-align: center;">  
                <h2 style="color: #4CAF50;">🚍 Welcome, %s! 🎉</h2>  
                <p>We are excited to have you onboard. 🚀</p>  
                <p>With <strong>Enjoy Travelling With Us</strong>, you can book your bus tickets with ease.</p>  
                <p>✅ Secure & Easy Bookings<br>✅ Real-time Seat Availability<br>✅ Hassle-free Travel</p>  
                <p>Start exploring now and enjoy seamless travel experiences!</p>  
                <p>📌 <strong>Visit our website to get started.</strong></p>  
                <p>Best regards,<br><strong>Enjoy Travelling With Us Team</strong></p>  
            </body>  
            </html>  
        """.formatted(userName);  
        // 📌 HTML email template madhun user cha naav insert kela  

        sendEmail(toEmail, subject, body);  
        // 📌 Email send karnyasathi common method vaparla  
    }  

    /**
     * ✅ Sends OTP Email.
     * - **Security Alert:** OTP confidential ahe, mhanun warn message add kela.
     */
    @Override  
    public void sendOTP(String toEmail, String otp) {  
        String subject = "🔐 Your OTP Code";  
        String body = """  
            <html>  
            <body style="font-family:Arial,sans-serif;">  
                <h2>Hello,</h2>  
                <p>Your OTP for verification is: <strong>%s</strong></p>  
                <p style="color:red;"><b>Do not share this OTP with anyone.</b></p>  
                <p>Thank you!</p>  
            </body>  
            </html>  
        """.formatted(otp);  

        sendEmail(toEmail, subject, body);  
    }  

    /**
     * ✅ Sends update confirmation email after a user updates their details.
     */
    @Override  
    public void sendUpdateConfirmation(String toEmail, String updateType) {  
        String subject = "✅ Account Update Successful";  
        String body = """  
            <html>  
            <body style="font-family: Arial, sans-serif; text-align: center;">  
                <h3>🔄 Your %s has been updated successfully!</h3>  
                <p>If you did not request this change, please contact support immediately.</p>  
                <p>Best regards,<br><strong>Enjoy Travelling With Us Team</strong></p>  
            </body>  
            </html>  
        """.formatted(updateType);  

        sendEmail(toEmail, subject, body);  
    }  

    /**
     * ✅ Booking Confirmation Email.
     */
    @Override  
    public void sendBookingConfirmation(String toEmail, String busDetails, String bookingId) {  
        String subject = "🚌 Booking Confirmed!";  
        String body = """  
            <html>  
            <body>  
                <h2>🎉 Your booking is confirmed! 🎉</h2>  
                <p>🚌 <strong>Bus Details:</strong> %s</p>  
                <p>📌 <strong>Booking ID:</strong> %s</p>  
                <p>✅ Please arrive 30 minutes before departure.</p>  
                <p>Thank you for choosing our service! 🚍</p>  
            </body>  
            </html>  
        """.formatted(busDetails, bookingId);  

        sendEmail(toEmail, subject, body);  
    }  

    /**
     * ✅ Bus Arrival Reminder Email.
     */
    @Override  
    public void sendArrivalReminder(String toEmail, String busDetails) {  
        String subject = "🚏 Your Bus is Arriving Soon!";  
        String body = """  
            <html>  
            <body>  
                <h2>🔔 Reminder! Your bus is arriving soon.</h2>  
                <p>🚌 <strong>Bus Details:</strong> %s</p>  
                <p>⏳ Please be ready at your stop.</p>  
                <p>Safe travels! 🚍</p>  
            </body>  
            </html>  
        """.formatted(busDetails);  

        sendEmail(toEmail, subject, body);  
    }  

    /**
     * ✅ Review Request Email.
     */
    @Override  
    public void sendReviewRequest(String toEmail, String busDetails) {  
        String subject = "⭐ Rate Your Trip!";  
        String body = """  
            <html>  
            <body>  
                <h2>🙏 We hope you had a great journey! 🚍</h2>  
                <p>🚌 <strong>Bus Details:</strong> %s</p>  
                <p>📝 Please take a moment to rate and review your experience.</p>  
                <p>🔗 <a href="#">Click here to submit your review</a></p>  
            </body>  
            </html>  
        """.formatted(busDetails);  

        sendEmail(toEmail, subject, body);  
    }  

    /**
     * ✅ Booking Cancellation Email.
     */
    @Override  
    public void sendBookingCancellation(String toEmail, String busDetails) {  
        String subject = "❌ Booking Cancelled";  
        String body = """  
            <html>  
            <body>  
                <h2>⚠️ Your booking has been cancelled.</h2>  
                <p>🚌 <strong>Bus Details:</strong> %s</p>  
                <p>💰 Any applicable refund will be processed shortly.</p>  
                <p>We hope to see you again soon!</p>  
            </body>  
            </html>  
        """.formatted(busDetails);  

        sendEmail(toEmail, subject, body);  
    }  
}  
