package com.sunbeam.booking.service;  
// 📌 Service layer madhil package declaration  

import java.util.List;  
import java.util.stream.Collectors;  
// 📌 List and Stream API use karnya sathi import  

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
// 📌 Logging sathi SLF4J library use kela  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
// 📌 Dependency Injection aani Service annotation use karnya sathi  

import com.sunbeam.booking.dto.NotificationDTO;  
import com.sunbeam.booking.entity.Notification;  
import com.sunbeam.booking.entity.User;  
import com.sunbeam.booking.exceptions.ResourceNotFoundException;  
import com.sunbeam.booking.repository.NotificationRepository;  
import com.sunbeam.booking.repository.UserRepository;  
// 📌 DTO, Entity, Exception, aani Repository classes import kele  

import jakarta.transaction.Transactional;  
// 📌 Database transaction maintain karnya sathi `@Transactional` annotation use kela  

@Service  
// 📌 Ha class ek **Spring Service Bean** ahe  

public class NotificationServiceImpl implements NotificationService {  

    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);  
    // 📌 Logger create kela **debugging aani tracking sathi**  

    @Autowired  
    private NotificationRepository notificationRepository;  
    // 📌 **Notification Repository** inject kela database madhun notifications fetch/save karnya sathi  

    @Autowired  
    private UserRepository userRepository;  
    // 📌 **User Repository** inject kela user exist kartoy ka he validate karnya sathi  

    /**
     * ✅ Saves a notification in the database.
     * - Ensures the user exists before creating a notification.
     */
    @Override  
    @Transactional  
    public void saveNotification(Long userId, String message) {  
        log.info("🔔 Saving notification for user ID: {}", userId);  
        // 📌 **User sathi notification save karnya sathi log**  

        User user = userRepository.findById(userId)  
                .orElseThrow(() -> {  
                    log.error("❌ User not found with ID: {}", userId);  
                    return new ResourceNotFoundException("User not found with ID: " + userId);  
                });  
        // 📌 **User validate karto**  
        // 🔹 User nahi sapadla tar `ResourceNotFoundException` throw karto  

        Notification notification = new Notification();  
        notification.setUser(user);  
        notification.setMessage(message);  
        // 📌 **Notification entity prepare karto**  

        notificationRepository.save(notification);  
        // 📌 **Database madhe notification save karto**  

        log.info("✅ Notification saved successfully for user ID: {}", userId);  
        // 📌 **Notification successful message log karto**  
    }  

    /**
     * ✅ Retrieves all notifications for a specific user.
     * - Uses optimized query for better performance.
     */
    @Transactional(rollbackOn = Exception.class)  
    @Override  
    public List<NotificationDTO> getUserNotifications(Long userId) {  
        log.info("📩 Fetching notifications for user ID: {}", userId);  
        // 📌 **User sathi notifications fetch karnya sathi log**  

        List<Notification> notifications = notificationRepository.findByUserId(userId);  
        // 📌 **Database madhun user specific notifications fetch karto**  

        return notifications.stream()  
            .map(n -> new NotificationDTO(n.getId(), n.getMessage(), n.isReadStatus(), n.getCreatedOn()))  
            .collect(Collectors.toList());  
        // 📌 **Entity to DTO conversion using Java Streams**  
        // 🔹 Notification objects la **NotificationDTO** madhe convert karto  
        // 🔹 `List<NotificationDTO>` return karto  
    }  
}
