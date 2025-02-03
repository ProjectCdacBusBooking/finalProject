package com.sunbeam.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.LoginDTO;
import com.sunbeam.booking.dto.UserDTO;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.entity.Wallet;
import com.sunbeam.booking.repository.UserRepository;
import com.sunbeam.booking.repository.WalletRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WalletRepository walletRepository;
    
    public User createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        
        // Create a wallet for the user
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0); // Initial balance
        walletRepository.save(wallet);
        
        return user;
    }

    public boolean registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return true;
    }

    public UserDTO loginUser(LoginDTO loginDTO) {
        List<User> users = userRepository.findByEmail(loginDTO.getEmail());
        if (!users.isEmpty()) {
            User user = users.get(0);
            if (loginDTO.getPassword().equals(user.getPassword())) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setEmail(user.getEmail());
                return userDTO;
            }
        }
        return null;
    }

    public boolean logoutUser(Long userId) {
        return true; // Assuming logout is always successful for simplicity
    }

    public UserDTO getUserProfile(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }
        return null;
    }

    public boolean updateUserProfile(Long userId, UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean changePassword(Long userId, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean sendPasswordResetOTP(String email) {
        return true; // Assuming OTP sending is always successful for simplicity
    }

    public boolean resetPassword(String email, String otp, String newPassword) {
        List<User> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            // Handle case where multiple users with the same email exist
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    user.setPassword(newPassword);
                    userRepository.save(user);
                    return true;
                }
            }
        }
        return false;
    }
}
