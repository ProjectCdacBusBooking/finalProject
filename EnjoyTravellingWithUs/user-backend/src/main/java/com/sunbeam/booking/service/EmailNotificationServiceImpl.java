//package com.sunbeam.booking.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class EmailNotificationServiceImpl implements EmailNotificationService {
//
//    private static final Logger log = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    /**
//     * सामान्य ईमेल सेंड करण्यासाठी (Simple Email Sending)
//     */
//    @Override
//    public void sendEmail(String to, String subject, String body) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(body, true);
//            helper.setFrom("your-email@gmail.com");
//
//            log.info("📧 Sending email to: {}", to);
//            log.info("📧 Subject: {}", subject);
//            log.info("📧 Body: {}", body);
//
//            mailSender.send(message);
//            log.info("✅ Email sent successfully!");
//        } catch (MessagingException | MailException e) {
//            log.error("❌ Email failed: {}", e.getMessage());
//        }
//    }
//
//    /**
//     * OTP साठी ईमेल (OTP Email)
//     */
//    @Override
//    public void sendOTP(String toEmail, String otp) {
//        String subject = "🔐 Your OTP Code";
//        String body = "Hello,\n\nYour OTP for verification is: " + otp + 
//                      "\nPlease do not share this with anyone.\n\nThank you!";
//        sendEmail(toEmail, subject, body);
//    }
//
//    /**
//     * बुकिंग कन्फर्मेशन ईमेल (Booking Confirmation Email)
//     */
//    @Override
//    public void sendBookingConfirmation(String toEmail, String busDetails, String bookingId) {
//        String subject = "🚌 Booking Confirmed!";
//        String body = "🎉 Your booking is confirmed! 🎉\n\n" +
//                      "🚌 Bus Details: " + busDetails + "\n" +
//                      "📌 Booking ID: " + bookingId + "\n\n" +
//                      "✅ Please arrive 30 minutes before departure.\n" +
//                      "Thank you for choosing our service! 🚍";
//        sendEmail(toEmail, subject, body);
//    }
//
//    /**
//     * बस आगमनाची सूचना (Bus Arrival Reminder)
//     */
//    @Override
//    public void sendArrivalReminder(String toEmail, String busDetails) {
//        String subject = "🚏 Your Bus is Arriving Soon!";
//        String body = "🔔 Reminder! Your bus is arriving soon.\n\n" +
//                      "🚌 Bus Details: " + busDetails + "\n" +
//                      "⏳ Please be ready at your stop.\n\n" +
//                      "Safe travels! 🚍";
//        sendEmail(toEmail, subject, body);
//    }
//
//    /**
//     * प्रवासानंतर रिव्ह्यू रिक्वेस्ट (Review Request)
//     */
//    @Override
//    public void sendReviewRequest(String toEmail, String busDetails) {
//        String subject = "⭐ Rate Your Trip!";
//        String body = "🙏 We hope you had a great journey! 🚍\n\n" +
//                      "🚌 Bus Details: " + busDetails + "\n\n" +
//                      "📝 Please take a moment to rate and review your experience.\n" +
//                      "🔗 Click here to submit your review: [Review Link]";
//        sendEmail(toEmail, subject, body);
//    }
//
//    /**
//     * बुकिंग कॅन्सलेशन ईमेल (Booking Cancellation Email)
//     */
//    @Override
//    public void sendBookingCancellation(String toEmail, String busDetails) {
//        String subject = "❌ Booking Cancelled";
//        String body = "⚠️ Your booking has been cancelled.\n\n" +
//                      "🚌 Bus Details: " + busDetails + "\n\n" +
//                      "💰 Any applicable refund will be processed shortly.\n" +
//                      "We hope to see you again soon!";
//        sendEmail(toEmail, subject, body);
//    }
//}


package com.sunbeam.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * ✅ Sends an email with UTF-8 encoding & proper HTML formatting.
     */
    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom("your-email@gmail.com");

            log.info("📧 Sending email to: {}", to);
            log.info("📧 Subject: {}", subject);

            mailSender.send(message);
            log.info("✅ Email sent successfully!");
        } catch (MessagingException | MailException e) {
            log.error("❌ Email failed: {}", e.getMessage());
        }
    }

    /**
     * ✅ Sends OTP Email.
     */
    @Override
    public void sendOTP(String toEmail, String otp) {
        String subject = "🔐 Your OTP Code";
        String body = """
            <html>
            <body>
                <p>Hello,</p>
                <p>Your OTP for verification is: <strong>%s</strong></p>
                <p>Please do not share this with anyone.</p>
                <p>Thank you!</p>
            </body>
            </html>
        """.formatted(otp);
        sendEmail(toEmail, subject, body);
    }

    /**
     * ✅ Sends Booking Confirmation Email.
     */
    @Override
    public void sendBookingConfirmation(String toEmail, String busDetails, String bookingId) {
        String subject = "🚌 Booking Confirmed!";
        String body = """
            <html>
            <body>
                <h3>🎉 Your booking is confirmed! 🎉</h3>
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
     * ✅ Sends Bus Arrival Reminder Email.
     */
    @Override
    public void sendArrivalReminder(String toEmail, String busDetails) {
        String subject = "🚏 Your Bus is Arriving Soon!";
        String body = """
            <html>
            <body>
                <h3>🔔 Reminder! Your bus is arriving soon.</h3>
                <p>🚌 <strong>Bus Details:</strong> %s</p>
                <p>⏳ Please be ready at your stop.</p>
                <p>Safe travels! 🚍</p>
            </body>
            </html>
        """.formatted(busDetails);
        sendEmail(toEmail, subject, body);
    }

    /**
     * ✅ Sends Review Request Email.
     */
    @Override
    public void sendReviewRequest(String toEmail, String busDetails) {
        String subject = "⭐ Rate Your Trip!";
        String body = """
            <html>
            <body>
                <h3>🙏 We hope you had a great journey! 🚍</h3>
                <p>🚌 <strong>Bus Details:</strong> %s</p>
                <p>📝 Please take a moment to rate and review your experience.</p>
                <p>🔗 <a href="#">Click here to submit your review</a></p>
            </body>
            </html>
        """.formatted(busDetails);
        sendEmail(toEmail, subject, body);
    }

    /**
     * ✅ Sends Booking Cancellation Email.
     */
    @Override
    public void sendBookingCancellation(String toEmail, String busDetails) {
        String subject = "❌ Booking Cancelled";
        String body = """
            <html>
            <body>
                <h3>⚠️ Your booking has been cancelled.</h3>
                <p>🚌 <strong>Bus Details:</strong> %s</p>
                <p>💰 Any applicable refund will be processed shortly.</p>
                <p>We hope to see you again soon!</p>
            </body>
            </html>
        """.formatted(busDetails);
        sendEmail(toEmail, subject, body);
    }
}
