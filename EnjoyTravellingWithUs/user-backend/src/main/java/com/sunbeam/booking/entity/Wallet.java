package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user") // ✅ Prevents infinite recursion
public class Wallet extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private double balance;

    /**
     * ✅ Method to safely update balance
     */
    public void updateBalance(double amount) {
        this.balance = Math.max(0, this.balance + amount); // ✅ Ensures no negative balance
    }
}
