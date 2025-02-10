package com.sunbeam.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.booking.dto.ApiResponse;
import com.sunbeam.booking.dto.AuthRequest;
import com.sunbeam.booking.dto.UserDTO;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.UserRepository;
import com.sunbeam.booking.util.DTOMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;
    
    @Autowired
    private EmailNotificationService emailNotificationService;

    /**
     * ✅ Registers a new user.
     */
    @Override
    @Transactional
    public ApiResponse register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            log.warn("❌ Registration failed: Email {} already exists!", userDTO.getEmail());
            return new ApiResponse("Email already registered!");
        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setContact(userDTO.getContact());
        user.setPassword(userDTO.getPassword()); // TODO: Hash password before storing
        emailNotificationService.sendWelcomeEmail(user.getEmail(), user.getFirstName());
        userRepository.save(user);

        log.info("✅ User registered successfully: {}", userDTO.getEmail());

        return new ApiResponse("User registered successfully");
    }

    /**
     * ✅ Handles user login.
     */
    @Override
    public UserDTO login(AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (authRequest.getPassword().equals(user.getPassword())) { // TODO: Implement hashed password check
                log.info("✅ User logged in: {}", authRequest.getEmail());
                return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getContact(), user.getPassword());
            }
        }
        log.warn("❌ Login failed for email: {}", authRequest.getEmail());
        return null;
    }

    /**
     * ✅ Retrieves all users.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return DTOMapper.toUserDTOList(userRepository.findAll());
    }


    /**
     * ✅ Updates a user’s profile.
     */
    @Override
    @Transactional
    public boolean updateUserProfile(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setContact(userDTO.getContact());
        emailNotificationService.sendUpdateConfirmation(user.getEmail(), "Profile Update");

        userRepository.save(user);

        log.info("✅ User profile updated: {}", userId);
        return true;
    }

    /**
     * ✅ Changes a user’s password.
     */
    @Override
    @Transactional
    public boolean changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        user.setPassword(newPassword); // TODO: Hash password before saving
        userRepository.save(user);

        log.info("✅ Password changed for user: {}", userId);
        return true;
    }

    /**
     * ✅ Sends OTP for password reset.
     */
    @Override
    public boolean sendPasswordResetOTP(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            otpService.generateOTP(email);
            log.info("✅ OTP sent to email: {}", email);
            return true;
        }
        log.warn("❌ Password reset failed: Email {} not found!", email);
        return false;
    }

    /**
     * ✅ Resets the user’s password after OTP verification.
     */
    @Override
    @Transactional
    public boolean resetPassword(String email, String otp, String newPassword) {
        if (otpService.validateOTP(email, otp)) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

            user.setPassword(newPassword); // TODO: Hash password before saving
            userRepository.save(user);

            log.info("✅ Password reset successful for email: {}", email);
            return true;
        }
        log.warn("❌ Password reset failed for email: {} (Invalid OTP)", email);
        return false;
    }

    /**
     * ✅ Retrieves user profile by ID.
     */
    @Override
    public UserDTO getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getContact(), user.getPassword());
    }

    /**
     * ✅ Deletes a user by ID.
     */
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("✅ User deleted: {}", userId);
    }

    /**
     * ✅ Logs out a user.
     */
    @Override
    @Transactional
    public boolean logoutUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        user.setLoggedIn(false);
        userRepository.save(user);

        log.info("✅ User logged out: {}", userId);
        return true;
    }
}
