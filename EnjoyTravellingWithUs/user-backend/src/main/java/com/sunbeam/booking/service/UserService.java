package com.sunbeam.booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.LoginDTO;
import com.sunbeam.booking.dto.UserDTO;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.repository.UserRepository;

import jakarta.transaction.Transactional;

/**
 * 📝 UserService - User Authentication संबंधित Service Methods
 * 📌 Database Operations Handle करतो.
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private final UserRepository userRepository = null;

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
    
    /**
     * ✅ User Login Method
     * 📌 Email & Password verify करून User ची माहिती परत पाठवतो.
     */
    public UserDTO loginUser(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            
            
            if (user.getPassword().equals(loginDTO.getPassword())) { 
                UserDTO userDTO = new UserDTO();
                userDTO.setFullName(user.getFullName());
                userDTO.setEmail(user.getEmail());
                userDTO.setPhone(user.getPhone());
                return userDTO; // Login Successful
            }
        }
        return null; // Invalid Credentials
    }
    
    /**
     * ✅ User Logout Method
     * 📌 User ला Logout करतो. (Session invalidate)
     */
    public boolean logoutUser(Long userId) {
        // जर User सापडला आणि त्याचा session successfully end झाला तर true return करतो.
        // TODO: Session management implement करायचं आहे.
        return true; // Placeholder for now.
    }
    
    /**
     * ✅ Get User Profile Method
     * 📌 User चं Profile डेटा Database कडून घेऊन UserDTO मध्ये Set करतो.
     */
    public UserDTO getUserProfile(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setFullName(user.getFullName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            return userDTO; // Return User Profile
        }
        return null; // Profile Not Found
    }
    
    /**
     * ✅ Update User Profile Method
     * 📌 User चं Profile Update करतो Database मध्ये.
     */
    public boolean updateUserProfile(Long userId, UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFullName(userDTO.getFullName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            userRepository.save(user); // Save Updated User
            return true;
        }
        return false; // User Not Found
    }
    
    /**
     * ✅ Change Password Method
     * 📌 User चं Password Update करतो Database मध्ये.
     */
    public boolean changePassword(Long userId, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword); // Password Update
            userRepository.save(user); // Save Updated User
            return true;
        }
        return false; // User Not Found
    }
}
