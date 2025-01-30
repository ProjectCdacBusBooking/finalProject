package com.sunbeam.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sunbeam.booking.service.WalletService;
import lombok.RequiredArgsConstructor;

/**
 * 📝 WalletController - Wallet संबंधित API Handlers
 * 📌 Check Wallet Balance, Add Money, Make Payment आणि Transaction History संबंधित API.
 */
@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    /**
     * ✅ Check Wallet Balance API
     * 📌 युजरच्या वॉलेटचे शिल्लक पाहण्यासाठी API.
     * 🟢 URL: GET /api/wallet/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Double> getWalletBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getWalletBalance(userId));
    }

    /**
     * ✅ Add Money to Wallet API
     * 📌 युजरच्या वॉलेटमध्ये पैसे जोडण्यासाठी API.
     * 🟢 URL: POST /api/wallet/add-money/{userId}
     */
    @PostMapping("/add-money/{userId}")
    public ResponseEntity<String> addMoneyToWallet(@PathVariable Long userId, @RequestParam double amount) {
        boolean success = walletService.addMoneyToWallet(userId, amount);
        if (success) {
            return ResponseEntity.ok("Money added successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to add money");
        }
    }

    /**
     * ✅ Make Payment via Wallet API
     * 📌 युजर वॉलेट वापरून पेमेंट करण्याची API.
     * 🟢 URL: POST /api/wallet/pay
     */
    @PostMapping("/pay")
    public ResponseEntity<String> makePayment(@RequestParam Long userId, @RequestParam double amount) {
        boolean success = walletService.makePayment(userId, amount);
        if (success) {
            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(400).body("Insufficient balance");
        }
    }

    /**
     * ✅ Transaction History API
     * 📌 युजरच्या ट्रांजेक्शन इतिहासाची API.
     * 🟢 URL: GET /api/wallet/transactions/{userId}
     */
    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> getTransactionHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getTransactionHistory(userId));
    }
}
