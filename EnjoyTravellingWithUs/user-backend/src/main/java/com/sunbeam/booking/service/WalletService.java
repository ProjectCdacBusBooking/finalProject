package com.sunbeam.booking.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 📝 WalletService - Wallet संबंधित सर्व Service Methods
 * 📌 वॉलेट बॅलन्स तपासणे, पैसे जोडणे, पेमेंट करणे, आणि ट्रांजेक्शन इतिहास मिळवणे.
 */
@Service
public class WalletService {

    // Mock Data for wallet balance and transaction history (This should be in a database in a real application)
    private final List<String> transactionHistory = new ArrayList<>();
    private final double initialBalance = 100.0; // Starting balance for each user

    /**
     * ✅ Get Wallet Balance Method
     * 📌 युजरचा वॉलेट बॅलन्स प्राप्त करण्यासाठी.
     */
    public double getWalletBalance(Long userId) {
        return initialBalance; // In a real scenario, you would fetch this from the database
    }

    /**
     * ✅ Add Money to Wallet Method
     * 📌 वॉलेटमध्ये पैसे जोडण्यासाठी.
     */
    public boolean addMoneyToWallet(Long userId, double amount) {
        if (amount > 0) {
            transactionHistory.add("Added " + amount + " to wallet");
            return true;
        }
        return false;
    }

    /**
     * ✅ Make Payment Method
     * 📌 वॉलेट वापरून पेमेंट प्रक्रिया करण्यासाठी.
     */
    public boolean makePayment(Long userId, double amount) {
        if (amount <= initialBalance) {
            transactionHistory.add("Paid " + amount + " via wallet");
            return true;
        }
        return false;
    }

    /**
     * ✅ Get Transaction History Method
     * 📌 युजरच्या ट्रांजेक्शन इतिहासाची माहिती मिळवण्यासाठी.
     */
    public List<String> getTransactionHistory(Long userId) {
        return transactionHistory; // Fetch from database in a real application
    }
}
