package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sunbeam.booking.dto.UserDTO;
import com.sunbeam.booking.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 *  AuthController - User Authentication related API Handlers
 *  Register, Login, Logout, Profile Management - Controller API.
 */
@RestController
@RequestMapping("/user")
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
}
