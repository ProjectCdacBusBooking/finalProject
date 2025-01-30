package com.sunbeam.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sunbeam.booking.entity.Wallet;
import com.sunbeam.booking.repository.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    /**
     * ✅ Check Wallet Balance Method
     */
    public double checkBalance(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null) {
            return wallet.getBalance();
        }
        return 0.0; // User not found, return 0 balance
    }
    
    /**
     * ✅ Add Money Method
     */
    public boolean addMoney(Long userId, double amount) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null) {
            System.out.println("Current Balance: ₹" + wallet.getBalance()); // Debugging
            wallet.setBalance(wallet.getBalance() + amount);
            walletRepository.save(wallet);
            return true;
        }
        return false; // User not found, fail the operation
    }
    
    /**
     * ✅ Make Payment Method
     * 📌 युजरच्या वॉलेटमध्ये पेमेंट कमी करण्यासाठी वापरला जातो.
     */
    public boolean makePayment(Long userId, double amount) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null && wallet.getBalance() >= amount) {
            wallet.setBalance(wallet.getBalance() - amount);
            walletRepository.save(wallet);
            return true;
        }
        return false; // Insufficient balance or user not found
    }
    
    /**
     * ✅ Get Transaction History Method
     * 📌 युजरच्या वॉलेट ट्रांझॅक्शन हिस्ट्री परत आणण्यासाठी वापरला जातो.
     */
    public List<String> getTransactionHistory(Long userId) {
        // Ideally, this should be retrieved from a Transaction entity or separate table
        // For now, returning mock data as an example
        List<String> transactions = new ArrayList<>();
        transactions.add("Added ₹500 on 2025-01-01");
        transactions.add("Paid ₹200 for booking on 2025-01-05");
        transactions.add("Added ₹300 on 2025-01-10");
        return transactions;
    }
}
