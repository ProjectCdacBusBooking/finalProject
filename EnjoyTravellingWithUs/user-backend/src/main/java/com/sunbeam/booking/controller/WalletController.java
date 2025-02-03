package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.booking.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{userId}")
    public ResponseEntity<Double> getWalletBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getWalletBalance(userId));
    }

    @PostMapping("/add-money/{userId}")
    public ResponseEntity<String> addMoneyToWallet(@PathVariable Long userId, @RequestParam double amount) {
        boolean success = walletService.addMoneyToWallet(userId, amount);
        if (success) {
            return ResponseEntity.ok("Money added successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to add money");
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<String> makePayment(@RequestParam Long userId, @RequestParam double amount) {
        boolean success = walletService.makePayment(userId, amount);
        if (success) {
            return ResponseEntity.ok("Payment successful");
        } else {
            return ResponseEntity.status(400).body("Insufficient balance");
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<?> getTransactionHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getTransactionHistory(userId));
    }
}
