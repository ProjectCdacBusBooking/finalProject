package com.sunbeam.booking.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.dto.WalletDTO;
import com.sunbeam.booking.entity.User;
import com.sunbeam.booking.entity.Wallet;
import com.sunbeam.booking.exceptions.ResourceNotFoundException;
import com.sunbeam.booking.repository.UserRepository;
import com.sunbeam.booking.repository.WalletRepository;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    public WalletDTO getUserWallet(Long userId) {
        Optional<Wallet> walletOpt = walletRepository.findByUserId(userId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.setUserId(wallet.getUser().getId());
            walletDTO.setBalance(wallet.getBalance());
            return walletDTO;
        }
        return null;
    }

    public boolean addFunds(Long userId, double amount) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Wallet wallet = user.getWallet();
            if (wallet != null) {
                wallet.setBalance(wallet.getBalance() + amount);
                walletRepository.save(wallet);
                return true;
            }
        }
        return false;
    }

    /*
    public boolean withdrawFunds(Long userId, double amount) {
        Optional<Wallet> walletOpt = walletRepository.findByUserId(userId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            if (wallet.getBalance() >= amount) {
                wallet.setBalance(wallet.getBalance() - amount);
                walletRepository.save(wallet);
                return true;
            } else {
                throw new RuntimeException("Insufficient funds");
            }
        }
        throw new RuntimeException("Wallet not found");
    }
    */
    public boolean withdrawFunds(Long userId, double amount) {
        logger.info("Withdrawing funds for user ID: {}", userId);
        Optional<Wallet> walletOpt = walletRepository.findByUserId(userId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            if (wallet.getBalance() >= amount) {
                wallet.setBalance(wallet.getBalance() - amount);
                walletRepository.save(wallet);
                return true;
            } else {
                logger.warn("Insufficient funds for user ID: {}", userId);
                throw new ResourceNotFoundException("Insufficient funds");
            }
        } else {
            logger.warn("Wallet not found for user ID: {}", userId);
            throw new ResourceNotFoundException("Wallet not found");
        }
    }

    public boolean deductFunds(Long userId, double amount) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Wallet wallet = user.getWallet();
            if (wallet != null && wallet.getBalance() >= amount) {
                wallet.setBalance(wallet.getBalance() - amount);
                walletRepository.save(wallet);
                return true;
            } else {
                throw new RuntimeException("Insufficient funds");
            }
        }
        throw new RuntimeException("Wallet not found");
    }
    
    /*
    public WalletDTO getWalletByUserId(Long userId) {
        Optional<Wallet> walletOpt = walletRepository.findByUserId(userId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
        } else {
            // Create wallet if it doesn't exist
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Wallet wallet = new Wallet();
                wallet.setUser(user);
                wallet.setBalance(0.0); // Initial balance
                walletRepository.save(wallet);
                return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
            }
        }
        return null; // Return null if user not found
    }
    */
    
    /*
    public WalletDTO getWalletByUserId(Long userId) {
        Optional<Wallet> walletOpt = walletRepository.findByUserId(userId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
        } else {
            // Create wallet if it doesn't exist
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Wallet wallet = new Wallet();
                wallet.setUser(user);
                wallet.setBalance(0.0); // Initial balance
                walletRepository.save(wallet);
                return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
            } else {
                throw new ResourceNotFoundException("User not found");
            }
        }
    }
    */
    
    public WalletDTO getWalletByUserId(Long userId) {
        logger.info("Fetching wallet for user ID: {}", userId);
        Optional<Wallet> walletOpt = walletRepository.findByUserId(userId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
        } else {
            logger.warn("Wallet not found for user ID: {}", userId);
            // Create wallet if it doesn't exist
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                Wallet wallet = new Wallet();
                wallet.setUser(user);
                wallet.setBalance(0.0); // Initial balance
                walletRepository.save(wallet);
                return new WalletDTO(wallet.getUser().getId(), wallet.getBalance());
            } else {
                throw new ResourceNotFoundException("User not found");
            }
        }
    }
    
    
}
