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
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Keeping React frontend compatibility
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * ✅ Retrieves all notifications for a user
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDTO>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    /**
     * ✅ Saves a new notification for a user
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveNotification(@RequestParam Long userId, @RequestParam String message) {
        notificationService.saveNotification(userId, message);
        return ResponseEntity.ok(new ApiResponse("✅ Notification saved successfully!"));
    }

}
