package com.sunbeam.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunbeam.booking.entity.Notification;
import com.sunbeam.booking.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * ✅ Subscribe to Notifications (Creates a welcome notification)
     */
    public boolean subscribeToNotifications(Long userId) {
        Notification notification = Notification.builder()
                .userId(userId)
                .message("🎉 Welcome! You are now subscribed to notifications.")
                .readStatus(false)
                .build();
        notificationRepository.save(notification);
        return true;
    }

    /**
     * ✅ Get Notifications for a User
     */
    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    /**
     * ✅ Mark a Notification as Read
     */
    public boolean markAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setReadStatus(true);
            notificationRepository.save(notification);
            return true;
        }).orElse(false);
    }
}
