package com.sunbeam.booking.controller;  
// 📌 he package declaration ahe, je sangta ki ha controller class "com.sunbeam.booking.controller" ya package madhe ahe

import com.sunbeam.booking.dto.*;  
// 📌 he import statements ahet je DTO (Data Transfer Object) classes la import kartat  
// 📌 DTO cha use data transfer sathi hota aani te request & response madhe madhyasthi mhanun vaparle jatat

import com.sunbeam.booking.exceptions.ResourceNotFoundException;  
// 📌 he exception handling sathi ahe, jar kahi resource sapdla nahi tr ha exception throw honar

import com.sunbeam.booking.service.UserService;  
// 📌 UserService he service layer ahe, je business logic handle karte

import jakarta.validation.Valid;  
// 📌 @Valid annotation use karun request madhil input data validate karnyasathi vaprle jate

import org.springframework.beans.factory.annotation.Autowired;  
// 📌 @Autowired annotation cha use dependency injection sathi hota, jevha service la controller madhe inject karaycha asel 

import org.springframework.http.ResponseEntity;  
// 📌 ResponseEntity cha use HTTP responses la wrap karnyasathi hota

import org.springframework.web.bind.annotation.*;  
// 📌 he Spring Boot madhil REST API create karnyasathi lagat asleli annotations ahet (@RestController, @RequestMapping, etc.)

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
// 📌 he logging sathi use kele jate, je logs console var print kartat

import java.util.List;  
// 📌 List cha use karun multiple users chi list return karaychi asel tar te vaprata yet

@RestController  
// 📌 he annotation sangta ki ha class ek REST API controller ahe

@RequestMapping("/api/users")  
// 📌 ya controller madhe "/api/users" he base URL asnar ahe  

@CrossOrigin(origins = "http://localhost:3000")  
// 📌 Cross-Origin Resource Sharing (CORS) enable karto frontend localhost:3000 sathi  

public class UserController {  
// 📌 ha main controller class ahe je user related operations handle karte  

    private static final Logger log = LoggerFactory.getLogger(UserController.class);  
    // 📌 ha logger ahe jo debug aani info messages console var print karto  

    @Autowired  
    private UserService userService;  
    // 📌 ha UserService cha instance ahe jo @Autowired mule automatically inject hoil  

    /**  
     * ✅ **User Registration**  
     */  
    @PostMapping("/register")  
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDTO userDTO) {  
        // 📌 ha method nava user register karayla use hota  
        log.info("📌 Registering new user: {}", userDTO.getEmail());  
        // 📌 log madhe email print honar, jo user register hotoy  
        ApiResponse response = userService.register(userDTO);  
        // 📌 user register karayla service call kartoy  
        return ResponseEntity.ok(response);  
        // 📌 response return karto je user la sangel ki registration successful zhala  
    }  

    /**  
     * ✅ **User Login**  
     */  
    @PostMapping("/login")  
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {  
        // 📌 ha method user login sathi ahe  
        log.info("📌 Attempting login for email: {}", authRequest.getEmail());  
        // 📌 login attempt cha log  
        UserDTO user = userService.login(authRequest);  
        // 📌 user login service la call karto  

        if (user == null) {  
            throw new ResourceNotFoundException("❌ Invalid email or password!");  
            // 📌 jar user sapdla nahi tar error throw honar  
        }  

        String jwtToken = generateJWTToken(user); // 🔹 Implement JWT Logic  
        // 📌 ek JWT token generate karnyasathi method call keli ahe  

        return ResponseEntity.ok(new AuthResponse(jwtToken, true, user));  
        // 📌 authenticated user la response madhe token return karto  
    }  

    // ✅ Utility method to generate JWT  
    private String generateJWTToken(UserDTO user) {  
        return "real-jwt-token"; // Replace with actual JWT logic  
        // 📌 ha dummy JWT token return karto, actual logic ithe implement karavi lagel  
    }  

    /**  
     * ✅ **Get All Users**  
     */  
    @GetMapping("/all")  
    public ResponseEntity<List<UserDTO>> getAllUsers() {  
        // 📌 ha method sagle users get karnyasathi ahe  
        log.info("📌 Fetching all users");  
        // 📌 log madhe print hoil ki sagle users fetch hotayat  
        List<UserDTO> users = userService.getAllUsers();  
        // 📌 service la call karun sagle users ghetoy  
        return ResponseEntity.ok(users);  
        // 📌 response madhe users chi list return karto  
    }  

    /**  
     * ✅ **Get User Profile by ID**  
     */  
    @GetMapping("/{userId}")  
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {  
        // 📌 ha method user profile fetch karnyasathi ahe  
        log.info("📌 Fetching profile for user ID: {}", userId);  
        // 📌 log madhe user ID print hoil  
        UserDTO userDTO = userService.getUserProfile(userId);  
        // 📌 service madhun user profile ghetoy  
        return ResponseEntity.ok(userDTO);  
        // 📌 user profile response madhe pathvtoy  
    }  

    /**  
     * ✅ **Update User Profile**  
     */  
    @PutMapping("/{userId}/update")  
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {  
        // 📌 ha method user profile update karnyasathi ahe  
        log.info("📌 Updating user ID: {}", userId);  
        // 📌 log madhe user ID print hoil jo update hotoy  
        boolean updated = userService.updateUserProfile(userId, userDTO);  
        // 📌 service method call karun user update karto  
        if (!updated) throw new ResourceNotFoundException("User not found with ID: " + userId);  
        // 📌 jar user sapdla nahi tar error throw karto  
        return ResponseEntity.ok(new ApiResponse("User updated successfully"));  
        // 📌 response return karto ki update successful zhala  
    }  

    /**  
     * ✅ **Change Password**  
     */  
    @PutMapping("/{userId}/change-password")  
    public ResponseEntity<ApiResponse> changePassword(@PathVariable Long userId, @RequestParam String newPassword) {  
        // 📌 ha method password change karnyasathi ahe  
        log.info("📌 Changing password for user ID: {}", userId);  
        boolean success = userService.changePassword(userId, newPassword);  
        if (!success) throw new ResourceNotFoundException("User not found with ID: " + userId);  
        return ResponseEntity.ok(new ApiResponse("Password changed successfully"));  
    }  

    /**  
     * ✅ **Logout User**  
     */  
    @PostMapping("/{userId}/logout")  
    public ResponseEntity<ApiResponse> logoutUser(@PathVariable Long userId) {  
        // 📌 ha method user logout karnyasathi ahe  
        log.info("📌 Logging out user ID: {}", userId);  
        boolean success = userService.logoutUser(userId);  
        if (!success) throw new ResourceNotFoundException("User not found with ID: " + userId);  
        return ResponseEntity.ok(new ApiResponse("User logged out successfully"));  
        // 📌 response return karto ki user logout jhala  
    }  
}  
