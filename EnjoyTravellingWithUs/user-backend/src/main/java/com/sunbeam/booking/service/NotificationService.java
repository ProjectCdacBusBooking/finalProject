package com.sunbeam.booking.service;

import java.util.List;

import com.sunbeam.booking.dto.NotificationDTO;

public interface NotificationService {
    void saveNotification(Long userId, String message);
    //List<Notification> getUserNotifications(Long userId);
    List<NotificationDTO> getUserNotifications(Long userId);
}
