package com.sunbeam.booking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sunbeam.booking.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    /**
     * ✅ Optimized Query to Fetch Wallet by User ID
     * 
     * - Uses **JOIN FETCH** to also fetch the related User entity in **one query**.
     * - Prevents the **N+1 problem**, making database queries more efficient.
     */
    @Query("SELECT w FROM Wallet w JOIN FETCH w.user WHERE w.user.id = :userId")
    Optional<Wallet> findByUserId(Long userId); // ✅ Optimized method
}
