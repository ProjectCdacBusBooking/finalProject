package com.sunbeam.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.NotificationDTO;
import com.sunbeam.booking.entity.Notification;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.NotificationRepository;
import com.sunbeam.booking.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * ✅ Saves a notification in the database.
     * - Ensures the user exists before creating a notification.
     */
    @Override
    @Transactional
    public void saveNotification(Long userId, String message) {
        log.info("🔔 Saving notification for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("❌ User not found with ID: {}", userId);
                    return new ResourceNotFoundException("User not found with ID: " + userId);
                });

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);

        notificationRepository.save(notification);
        log.info("✅ Notification saved successfully for user ID: {}", userId);
    }

    /**
     * ✅ Retrieves all notifications for a specific user.
     * - Uses optimized query for better performance.
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public List<NotificationDTO> getUserNotifications(Long userId) {
        log.info("📩 Fetching notifications for user ID: {}", userId);
        
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        
        return notifications.stream()
            .map(n -> new NotificationDTO(n.getId(), n.getMessage(), n.isReadStatus(), n.getCreatedOn()))
            .collect(Collectors.toList()); // ✅ Fix return type
    }




}
