package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sunbeam.booking.entity.User;
import java.util.Optional;

/**
 * 📝 UserRepository - Database Queries Handle करतो.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
