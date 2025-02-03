package com.sunbeam.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.WalletDTO;
import com.sunbeam.booking.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
@CrossOrigin(origins = "http://localhost:3000")
public class WalletController {
    @Autowired
    private WalletService walletService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserWallet(@PathVariable Long userId) {
        WalletDTO walletDTO = walletService.getWalletByUserId(userId);
        if (walletDTO != null) {
            return ResponseEntity.ok(walletDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
        }
    }

    

    @PostMapping("/add-funds")
    public ResponseEntity<String> addFunds(@RequestParam Long userId, @RequestParam double amount) {
        boolean isAdded = walletService.addFunds(userId, amount);
        if (isAdded) {
            return ResponseEntity.ok("✅ Funds Added Successfully!");
        }
        return ResponseEntity.status(400).body("❌ Adding Funds Failed!");
    }
    
    @PostMapping("/withdraw-funds")
    public ResponseEntity<String> withdrawFunds(@RequestParam Long userId, @RequestParam double amount) {
        try {
            boolean isWithdrawn = walletService.withdrawFunds(userId, amount);
            if (isWithdrawn) {
                return ResponseEntity.ok("✅ Funds Withdrawn Successfully!");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(400).body("❌ Withdrawing Funds Failed!");
    }

    @PostMapping("/deduct-funds")
    public ResponseEntity<String> deductFunds(@RequestParam Long userId, @RequestParam double amount) {
        try {
            boolean isDeducted = walletService.deductFunds(userId, amount);
            if (isDeducted) {
                return ResponseEntity.ok("✅ Funds Deducted Successfully!");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(400).body("❌ Deducting Funds Failed!");
    }
}
