package com.sunbeam.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.MoneyRequest;
import com.sunbeam.booking.service.WalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    /**
     * ✅ Check Wallet Balance API
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> checkWalletBalance(@PathVariable Long userId) {
        double balance = walletService.checkBalance(userId);
        return ResponseEntity.ok("Your current wallet balance is: ₹" + balance);
    }
    
    /**
     * ✅ Add Money to Wallet API
     */
    @PostMapping("/add-money/{userId}")
    public ResponseEntity<?> addMoneyToWallet(@PathVariable Long userId, @RequestBody MoneyRequest moneyRequest) {
        System.out.println("Received amount: ₹" + moneyRequest.getAmount()); // Debugging
        boolean success = walletService.addMoney(userId, moneyRequest.getAmount());
        if (success) {
            return ResponseEntity.ok("Successfully added ₹" + moneyRequest.getAmount() + " to your wallet.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add money to the wallet.");
        }
    }
    
    /**
     * ✅ Make Payment via Wallet API
     * 📌 युजरच्या वॉलेट वापरून पेमेंट करण्यासाठी वापरला जातो.
     * 🟢 URL: POST /api/wallet/pay
     */
    @PostMapping("/pay")
    public ResponseEntity<?> makePayment(@RequestParam Long userId, @RequestParam double amount) {
        boolean success = walletService.makePayment(userId, amount);
        if (success) {
            return ResponseEntity.ok("Payment of ₹" + amount + " was successful.");
        } else {
            return ResponseEntity.badRequest().body("Insufficient balance or payment failed.");
        }
    }
    
    /**
     * ✅ Get Transaction History API
     * 📌 युजरच्या वॉलेट ट्रांझॅक्शन हिस्ट्रीसाठी वापरला जातो.
     * 🟢 URL: GET /api/wallet/transactions/{userId}
     */
    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<String>> getTransactionHistory(@PathVariable Long userId) {
        List<String> transactions = walletService.getTransactionHistory(userId);
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
