package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.entity.Notification;
import com.sunbeam.booking.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * ✅ Subscribe to Notifications API
     */
    @PostMapping("/subscribe/{userId}")
    public ResponseEntity<String> subscribeToNotifications(@PathVariable Long userId) {
        boolean subscribed = notificationService.subscribeToNotifications(userId);
        return subscribed
                ? ResponseEntity.ok("✅ Successfully subscribed to notifications")
                : ResponseEntity.badRequest().body("❌ Subscription failed");
    }

    /**
     * ✅ Get User Notifications API
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotifications(userId));
    }

    /**
     * ✅ Mark Notification as Read API
     */
    @PutMapping("/read/{notificationId}")
    public ResponseEntity<String> markAsRead(@PathVariable Long notificationId) {
        boolean updated = notificationService.markAsRead(notificationId);
        return updated
                ? ResponseEntity.ok("✅ Notification marked as read")
                : ResponseEntity.badRequest().body("❌ Failed to mark notification as read");
    }
}
