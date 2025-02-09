package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String email;  // ✅ User email for authentication
    private String password;  // ✅ Plain-text password (should be hashed before storing)
}
