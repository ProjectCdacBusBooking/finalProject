package com.sunbeam.booking.service;

// Impoorts karun lagatache dependency lavt aahe
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.booking.dto.WalletDTO;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.entity.Wallet;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.UserRepository;
import com.sunbeam.booking.repository.WalletRepository;

// @Service mhanje ha ek service class aahe jo business logic handle karto
@Service
public class WalletServiceImpl implements WalletService {
    
    // WalletRepository chi dependency inject keli aahe
    @Autowired
    private WalletRepository walletRepository;
    
    // UserRepository chi dependency inject keli aahe
    @Autowired
    private UserRepository userRepository;
    
    // Logging sathi SLF4J cha use kart aahe
    private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    /**
     * ✅ Wallet madhe fund add karanyasathi hi method aahe.
     * - User la jar wallet nasel tar te create karto.
     */
    @Override
    @Transactional  // Transaction safe banavinyasathi ha annotation vaprt aahe
    public boolean addFunds(Long userId, double amount) {
        logger.info("Adding ₹{} to wallet for user ID: {}", amount, userId);
        
        // User cha wallet check karto, nasel tar create karto
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseGet(() -> createNewWallet(userId));

        // Wallet cha balance update karto
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);  // Database madhe wallet save karto
        
        logger.info("✅ Funds added successfully. New balance: ₹{}", wallet.getBalance());
        return true;  // Fund successfully add zale asha indication sathi
    }

    /**
     * ✅ Wallet madhun fund withdraw karanyasathi hi method aahe.
     * - Balance sufficient aahe ka te check karto.
     */
    @Override
    @Transactional  // Database madhil transaction manage karanyasathi
    public boolean withdrawFunds(Long userId, double amount) {
        logger.info("Withdrawing ₹{} for user ID: {}", amount, userId);
        
        // Wallet database madhe exist karto ka te check karto
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        // Balance enough aahe ka nahi check karto
        if (wallet.getBalance() < amount) {
            logger.warn("❌ Insufficient funds for user ID: {}", userId);
            throw new IllegalStateException("Insufficient funds");
        }

        // Balance madhun given amount subtract karto
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);  // Database madhe update karto
        
        logger.info("✅ Withdrawal successful. New balance: ₹{}", wallet.getBalance());
        return true;  // Successfully withdrawal zale asha indication sathi
    }

    /**
     * ✅ User cha wallet gheun yenyasathi hi method aahe.
     * - Jar wallet nasel tar te create karto.
     */
    @Override
    public WalletDTO getWalletByUserId(Long userId) {
        logger.info("Fetching wallet for user ID: {}", userId);

        // Wallet get karto, jar nasel tar navin create karto
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseGet(() -> createNewWallet(userId));

        // WalletDTO madhe data convert karun return karto
        return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
    }

    /**
     * ✅ Private helper method jo user cha wallet nasel tar create karto.
     */
    private Wallet createNewWallet(Long userId) {
        logger.warn("⚠️ Wallet not found for user ID: {}. Creating a new one.", userId);
        
        // User database madhe exist karto ka te check karto, nahi asel tar exception throw karto
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Navin wallet banavto ani 0 balance assign karto
        Wallet newWallet = new Wallet();
        newWallet.setUser(user);
        newWallet.setBalance(0.0); // Suruvatila balance zero asel
        walletRepository.save(newWallet);  // Navin wallet save karto
        
        logger.info("✅ New wallet created for user ID: {}", userId);
        return newWallet;  // Navin wallet return karto
    }
}
