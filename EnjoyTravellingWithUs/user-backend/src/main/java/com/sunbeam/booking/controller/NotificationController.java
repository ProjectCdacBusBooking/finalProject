package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.ApiResponse;
import com.sunbeam.booking.dto.NotificationDTO;
import com.sunbeam.booking.service.NotificationService;

@RestController
@RequestMapping("/api/notifications") // Notification related APIs handle karnyasathi controller
@CrossOrigin(origins = "http://localhost:3000") // Frontend access allow karnyasathi CORS enable karto
public class NotificationController {

    private final NotificationService notificationService; // Notification service inject karto

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * ✅ Ekha user chya saglya notifications fetch karto
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDTO>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId)); // User ID nusar notifications return karto
    }

    /**
     * ✅ Naveen notification save karto ekha user sathi
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveNotification(@RequestParam Long userId, @RequestParam String message) {
        notificationService.saveNotification(userId, message); // User ID ani message gheun notification save karto
        return ResponseEntity.ok(new ApiResponse("✅ Notification saved successfully!")); // Success response return karto
    }
}
