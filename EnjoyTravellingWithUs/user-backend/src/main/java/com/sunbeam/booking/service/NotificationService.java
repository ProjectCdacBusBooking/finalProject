package com.sunbeam.booking.service;

import com.sunbeam.booking.entity.Notification;
import java.util.List;

public interface NotificationService {
    void saveNotification(Long userId, String message);
    List<Notification> getUserNotifications(Long userId);
}
