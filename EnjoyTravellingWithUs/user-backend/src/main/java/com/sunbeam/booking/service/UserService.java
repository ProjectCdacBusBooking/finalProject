package com.sunbeam.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunbeam.booking.dto.UserDTO;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * 📝 UserService - User Authentication संबंधित Service Methods
 * 📌 Database Operations Handle करतो.
 */
@Service
@Transactional
public class UserService {

	@Autowired
    private  UserRepository userRepository;

    /**
     * ✅ User Registration Method
     * 📌 DTO मधून Data घेऊन Entity मध्ये Convert करून Database मध्ये Save करतो.
     */
    public boolean registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return false; // Email jr Registered asel tr Registration Fail
        }

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());

        userRepository.save(user);
        return true;
    }
}
