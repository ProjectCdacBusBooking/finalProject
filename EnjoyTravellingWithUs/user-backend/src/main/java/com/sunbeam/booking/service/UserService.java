package com.sunbeam.booking.service;

import java.util.List;
import com.sunbeam.booking.dto.ApiResponse;
import com.sunbeam.booking.dto.AuthRequest;
import com.sunbeam.booking.dto.UserDTO;

public interface UserService {
    /**
     * ✅ Registers a new user.
     */
    ApiResponse register(UserDTO userDTO);

    /**
     * ✅ Logs in a user and returns user details.
     */
    UserDTO login(AuthRequest authRequest);

    /**
     * ✅ Retrieves all users.
     */
    List<UserDTO> getAllUsers();

    /**
     * ✅ Updates a user's profile.
     */
    boolean updateUserProfile(Long userId, UserDTO userDTO);

    /**
     * ✅ Changes the user's password.
     */
    boolean changePassword(Long userId, String newPassword);

    /**
     * ✅ Sends an OTP for password reset.
     */
    boolean sendPasswordResetOTP(String email);

    /**
     * ✅ Resets the user's password after OTP verification.
     */
    boolean resetPassword(String email, String otp, String newPassword);

    /**
     * ✅ Retrieves a user's profile by ID.
     */
    UserDTO getUserProfile(Long userId);

    /**
     * ✅ Deletes a user by ID.
     */
    void deleteUser(Long userId);

    /**
     * ✅ Logs out a user.
     */
    boolean logoutUser(Long userId);
}
