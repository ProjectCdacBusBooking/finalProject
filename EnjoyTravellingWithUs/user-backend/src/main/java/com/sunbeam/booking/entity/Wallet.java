package com.sunbeam.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 📝 Wallet - Wallet Entity class
 * 📌 वॉलेट संबंधित डेटा संग्रहित करण्यासाठी वापरला जातो.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;
    
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private double balance;
}