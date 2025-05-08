package com.sunbeam.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.booking.dto.ApiResponse;
import com.sunbeam.booking.dto.WalletDTO;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.service.WalletService;

@RestController
@RequestMapping("/api/wallets")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Keeping React frontend compatibility
public class WalletController {

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    /**
     * ✅ **Get Wallet by User ID**
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<WalletDTO> getWalletByUserId(@PathVariable Long userId) {
        log.info("📌 Fetching wallet for User ID: {}", userId);
        WalletDTO walletDTO = walletService.getWalletByUserId(userId);
        if (walletDTO == null) {
            log.warn("⚠️ Wallet not found for User ID: {}", userId);
            throw new ResourceNotFoundException("Wallet not found for User ID: " + userId);
        }
        return ResponseEntity.ok(walletDTO);
    }

    /**
     * ✅ **Add Funds to Wallet**
     */
    @PostMapping("/add-funds")
    public ResponseEntity<ApiResponse> addFunds(@RequestParam Long userId, @RequestParam double amount) {
        log.info("📌 Adding ₹{} to wallet for User ID: {}", amount, userId);
        walletService.addFunds(userId, amount); // Throws Exception if user not found
        return ResponseEntity.ok(new ApiResponse("✅ Funds added successfully."));
    }

    /**
     * ✅ **Withdraw Funds from Wallet**
     */

    @PostMapping("/withdraw-funds")
    public ResponseEntity<ApiResponse> withdrawFunds(@RequestParam Long userId, @RequestParam double amount) {
        log.info("📌 Withdrawing ₹{} from wallet for User ID: {}", amount, userId);
        walletService.withdrawFunds(userId, amount); // Throws Exception if user not found
        return ResponseEntity.ok(new ApiResponse("✅ Funds withdrawn successfully."));
    }


    
}
