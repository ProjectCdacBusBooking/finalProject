package com.sunbeam.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.booking.entity.Wallet;
import com.sunbeam.booking.repository.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public double getWalletBalance(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId);
        return wallet != null ? wallet.getBalance() : 0.0;
    }

    public boolean addMoneyToWallet(Long userId, double amount) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null && amount > 0) {
            wallet.setBalance(wallet.getBalance() + amount);
            walletRepository.save(wallet);
            return true;
        }
        return false;
    }

    public boolean makePayment(Long userId, double amount) {
        Wallet wallet = walletRepository.findByUserId(userId);
        if (wallet != null && wallet.getBalance() >= amount) {
            wallet.setBalance(wallet.getBalance() - amount);
            walletRepository.save(wallet);
            return true;
        }
        return false;
    }
}
