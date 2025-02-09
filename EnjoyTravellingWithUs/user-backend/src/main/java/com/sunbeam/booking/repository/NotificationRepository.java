package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sunbeam.booking.entity.Notification;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * ✅ Optimized Query to Fetch Notifications by User ID
     * 
     * - Uses **JOIN FETCH** to load the associated User entity in **one query**.
     * - Prevents the **N+1 problem**, making notification retrieval more efficient.
     */
    @Query("SELECT n FROM Notification n JOIN FETCH n.user WHERE n.user.id = :userId")
    List<Notification> findByUserId(Long userId); // ✅ Optimized method
}
