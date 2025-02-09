package com.sunbeam.booking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sunbeam.booking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * ✅ Optimized Query to Fetch User by Email
     * 
     * - Uses `JOIN FETCH` to load related entities efficiently.
     * - Prevents **N+1 problem**, reducing multiple queries to one.
     * - Fetches `wallet` and `bookings` in **a single query**.
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.wallet LEFT JOIN FETCH u.bookings WHERE u.email = :email")
    Optional<User> findByEmail(String email);
}
