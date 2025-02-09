package com.sunbeam.booking.service;

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

@Service
public class WalletServiceImpl implements WalletService {
    
    @Autowired
    private WalletRepository walletRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    /**
     * ✅ Adds funds to the wallet.
     * - Ensures the user has a wallet before adding funds.
     * - Creates a wallet if the user doesn't have one.
     */
    @Override
    @Transactional
    public boolean addFunds(Long userId, double amount) {
        logger.info("Adding ₹{} to wallet for user ID: {}", amount, userId);
        
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseGet(() -> createNewWallet(userId));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
        
        logger.info("✅ Funds added successfully. New balance: ₹{}", wallet.getBalance());
        return true;
    }

    /**
     * ✅ Withdraws funds from the wallet.
     * - Ensures sufficient balance before withdrawal.
     */
    @Override
    @Transactional
    public boolean withdrawFunds(Long userId, double amount) {
        logger.info("Withdrawing ₹{} for user ID: {}", amount, userId);
        
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        if (wallet.getBalance() < amount) {
            logger.warn("❌ Insufficient funds for user ID: {}", userId);
            throw new IllegalStateException("Insufficient funds");
        }

        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
        
        logger.info("✅ Withdrawal successful. New balance: ₹{}", wallet.getBalance());
        return true;
    }

    /**
     * ✅ Retrieves a user's wallet.
     * - Creates a wallet if the user doesn't have one.
     */
    @Override
    public WalletDTO getWalletByUserId(Long userId) {
        logger.info("Fetching wallet for user ID: {}", userId);

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseGet(() -> createNewWallet(userId));

        return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
    }

    /**
     * ✅ Private helper method to create a new wallet if it does not exist.
     */
    private Wallet createNewWallet(Long userId) {
        logger.warn("⚠️ Wallet not found for user ID: {}. Creating a new one.", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Wallet newWallet = new Wallet();
        newWallet.setUser(user);
        newWallet.setBalance(0.0); // Initial balance
        walletRepository.save(newWallet);
        
        logger.info("✅ New wallet created for user ID: {}", userId);
        return newWallet;
    }
}
