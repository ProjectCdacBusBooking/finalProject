package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sunbeam.booking.entity.Wallet;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
	@Query("SELECT w FROM Wallet w WHERE w.user.id = :userId")
    Optional<Wallet> findByUserId(@Param("userId") Long userId);
}
