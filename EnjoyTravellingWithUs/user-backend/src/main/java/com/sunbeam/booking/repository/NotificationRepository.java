package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunbeam.booking.entity.Notification;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}
