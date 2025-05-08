package com.sunbeam.booking.controller;

import com.sunbeam.booking.dto.*;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * ✅ **User Registration**
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("📌 Registering new user: {}", userDTO.getEmail());
        ApiResponse response = userService.register(userDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * ✅ **User Login**
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        log.info("📌 Attempting login for email: {}", authRequest.getEmail());
        UserDTO user = userService.login(authRequest);

        if (user == null) {
            throw new ResourceNotFoundException("❌ Invalid email or password!");
        }

        String jwtToken = generateJWTToken(user); // 🔹 Implement JWT Logic
        return ResponseEntity.ok(new AuthResponse(jwtToken, true, user));
    }

    // ✅ Utility method to generate JWT
    private String generateJWTToken(UserDTO user) {
        return "real-jwt-token"; // Replace with actual JWT logic
    }


    /**
     * ✅ **Get All Users**
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("📌 Fetching all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * ✅ **Get User Profile by ID**
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        log.info("📌 Fetching profile for user ID: {}", userId);
        UserDTO userDTO = userService.getUserProfile(userId);
        return ResponseEntity.ok(userDTO);
    }

    /**
     * ✅ **Update User Profile**
     */
    @PutMapping("/{userId}/update")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
        log.info("📌 Updating user ID: {}", userId);
        boolean updated = userService.updateUserProfile(userId, userDTO);
        if (!updated) throw new ResourceNotFoundException("User not found with ID: " + userId);
        return ResponseEntity.ok(new ApiResponse("User updated successfully"));
    }

    /**
     * ✅ **Change Password**
     */
    @PutMapping("/{userId}/change-password")
    public ResponseEntity<ApiResponse> changePassword(@PathVariable Long userId, @RequestParam String newPassword) {
        log.info("📌 Changing password for user ID: {}", userId);
        boolean success = userService.changePassword(userId, newPassword);
        if (!success) throw new ResourceNotFoundException("User not found with ID: " + userId);
        return ResponseEntity.ok(new ApiResponse("Password changed successfully"));
    }

    /**
     * ✅ **Send Password Reset OTP**
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> sendPasswordResetOTP(@RequestParam String email) {
        log.info("📌 Sending password reset OTP to: {}", email);
        boolean sent = userService.sendPasswordResetOTP(email);
        if (!sent) throw new ResourceNotFoundException("User not found with email: " + email);
        return ResponseEntity.ok(new ApiResponse("OTP sent successfully"));
    }

    /**
     * ✅ **Reset Password**
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        log.info("📌 Resetting password for email: {}", email);
        boolean success = userService.resetPassword(email, otp, newPassword);
        if (!success) throw new ResourceNotFoundException("Invalid OTP or email");
        return ResponseEntity.ok(new ApiResponse("Password reset successfully"));
    }

    /**
     * ✅ **Delete User**
     */
    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        log.info("📌 Deleting user ID: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully"));
    }

    /**
     * ✅ **Logout User**
     */
    @PostMapping("/{userId}/logout")
    public ResponseEntity<ApiResponse> logoutUser(@PathVariable Long userId) {
        log.info("📌 Logging out user ID: {}", userId);
        boolean success = userService.logoutUser(userId);
        if (!success) throw new ResourceNotFoundException("User not found with ID: " + userId);
        return ResponseEntity.ok(new ApiResponse("User logged out successfully"));
    }
}
