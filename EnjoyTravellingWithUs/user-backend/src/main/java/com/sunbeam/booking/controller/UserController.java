package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.sunbeam.booking.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 *  AuthController - User Authentication related API Handlers
 *  Register, Login, Logout, Profile Management - Controller API.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private  UserService userService;

    /**
     *  User Registration API
     *  new User db mdhe Store krto.
     *  URL: POST /api/users/register
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        boolean isRegistered = userService.registerUser(userDTO);
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Registration Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Registration Failed!");
        }
    }
    
    /**
     * ✅ User Login API
     * 📌 User Email & Password verify करून Authentication Handle करतो.
     * 🟢 URL: POST /api/users/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        UserDTO userDTO = userService.loginUser(loginDTO);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO); // Login Successful
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid Email or Password!");
        }
    }
    
    /**
     * ✅ User Logout API
     * 📌 User ला Logout करून Session invalidate करतो.
     * 🟢 URL: POST /api/users/logout
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam Long userId) {
        boolean isLoggedOut = userService.logoutUser(userId);
        if (isLoggedOut) {
            return ResponseEntity.ok("✅ Successfully Logged Out!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Logout Failed!");
        }
    }
    
    /**
     * ✅ Get User Profile API
     * 📌 User चं Profile डेटा परत करतो.
     * 🟢 URL: GET /api/users/profile/{userId}
     */
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserProfile(userId);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO); // Profile Found
        } else {
            return ResponseEntity.notFound().build(); // Profile Not Found
        }
    }
    
    /**
     * ✅ Update User Profile API
     * 📌 User चं Profile Update करतो.
     * 🟢 URL: PUT /api/users/update/{userId}
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        boolean isUpdated = userService.updateUserProfile(userId, userDTO);
        if (isUpdated) {
            return ResponseEntity.ok("✅ Profile Updated Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Profile Update Failed!");
        }
    }
    
    /**
     * ✅ Change Password API
     * 📌 User चं Password Update करतो.
     * 🟢 URL: POST /api/users/change-password/{userId}
     */
    @PostMapping("/change-password/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        boolean isChanged = userService.changePassword(userId, newPassword);
        if (isChanged) {
            return ResponseEntity.ok("✅ Password Changed Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Password Change Failed!");
        }
    }
    
    /**
     * ✅ Forgot Password API
     * 📌 User ला OTP पाठवतो, जेणेकरून तो नवीन पासवर्ड सेट करू शकेल.
     * 🟢 URL: POST /api/users/forgot-password
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        boolean isOTPSent = userService.sendPasswordResetOTP(email);
        if (isOTPSent) {
            return ResponseEntity.ok("✅ OTP Sent Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ OTP Sending Failed!");
        }
    }
}
