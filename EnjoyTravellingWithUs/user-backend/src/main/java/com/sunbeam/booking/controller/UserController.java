package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.booking.dto.LoginRequest;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/profile/{email}")
    public Optional<User> getUserProfile(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        Optional<User> userOptional = userService.getUserByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userService.verifyPassword(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok("Login Successful");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }
}
