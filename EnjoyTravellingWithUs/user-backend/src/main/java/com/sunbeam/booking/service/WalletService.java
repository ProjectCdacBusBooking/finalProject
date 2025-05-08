package com.sunbeam.booking.service;

import com.sunbeam.booking.dto.WalletDTO;

public interface WalletService {
    /**
     * ✅ Fetches the wallet details of a user.
     */
    WalletDTO getWalletByUserId(Long userId);

    /**
     * ✅ Adds funds to the user's wallet.
     */
    boolean addFunds(Long userId, double amount);

    /**
     * ✅ Withdraws funds from the user's wallet.
     */
    boolean withdrawFunds(Long userId, double amount);
}
