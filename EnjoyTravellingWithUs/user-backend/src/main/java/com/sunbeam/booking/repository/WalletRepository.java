package com.sunbeam.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.booking.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    // Find Wallet by User ID
    Wallet findByUserId(Long userId);
}
