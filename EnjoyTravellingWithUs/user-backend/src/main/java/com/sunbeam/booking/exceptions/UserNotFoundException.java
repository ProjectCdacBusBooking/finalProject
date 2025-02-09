package com.sunbeam.booking.exceptions;

/**
 * 🚨 **User Not Found Exception**
 * - Thrown when a user is not found in the database.
 */
public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
