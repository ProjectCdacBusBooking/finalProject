package com.sunbeam.booking.exceptions;

/**
 * 🚨 **Insufficient Funds Exception**
 * - Thrown when a wallet does not have enough balance.
 */
public class InsufficientFundsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String message) {
        super(message);
    }
}
