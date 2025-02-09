package com.sunbeam.booking.exceptions;

/**
 * 🚨 **Resource Not Found Exception**
 * - Used when an entity (e.g., user, bus, seat) is not found in the system.
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
