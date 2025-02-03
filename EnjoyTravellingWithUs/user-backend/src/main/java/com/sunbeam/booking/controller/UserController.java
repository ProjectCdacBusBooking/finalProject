package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.LoginDTO;
import com.sunbeam.booking.dto.UserDTO;
import com.sunbeam.booking.service.EmailNotificationService;
import com.sunbeam.booking.service.OTPService;
import com.sunbeam.booking.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Autowired
    private OTPService otpService;

    // User Registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
        boolean isRegistered = userService.registerUser(userDTO);
        if (isRegistered) {
            String otp = otpService.generateOTP();
            emailNotificationService.sendBookingConfirmation(
                userDTO.getEmail(),
                "Registration Successful",
                "Your registration is successful. Your OTP is: " + otp
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Registration Failed!");
        }
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        UserDTO userDTO = userService.loginUser(loginDTO);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO); // Login Successful
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid Email or Password!");
        }
    }

    // User Logout
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam Long userId) {
        boolean isLoggedOut = userService.logoutUser(userId);
        if (isLoggedOut) {
            return ResponseEntity.ok("✅ Successfully Logged Out!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Logout Failed!");
        }
    }

    // Get User Profile
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserProfile(userId);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO); // Profile Found
        } else {
            return ResponseEntity.notFound().build(); // Profile Not Found
        }
    }

    // Update User Profile
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        boolean isUpdated = userService.updateUserProfile(userId, userDTO);
        if (isUpdated) {
            return ResponseEntity.ok("✅ Profile Updated Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Profile Update Failed!");
        }
    }

    // Change Password
    @PostMapping("/change-password/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        boolean isChanged = userService.changePassword(userId, newPassword);
        if (isChanged) {
            return ResponseEntity.ok("✅ Password Changed Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Password Change Failed!");
        }
    }

    // Forgot Password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        boolean isOTPSent = userService.sendPasswordResetOTP(email);
        if (isOTPSent) {
            return ResponseEntity.ok("✅ OTP Sent Successfully!");
        } else {
            return ResponseEntity.status(400).body("❌ OTP Sending Failed!");
        }
    }

    // Reset Password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        boolean isPasswordReset = userService.resetPassword(email, otp, newPassword);
        if (isPasswordReset) {
            emailNotificationService.sendBookingConfirmation(
                email,
                "Password Reset Successful",
                "Your password has been successfully reset."
            );
            return ResponseEntity.ok("✅ Password Reset Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Invalid OTP or User Not Found!");
        }
    }
}
