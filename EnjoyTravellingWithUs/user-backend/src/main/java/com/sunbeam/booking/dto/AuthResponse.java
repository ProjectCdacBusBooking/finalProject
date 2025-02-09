package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;  // ✅ JWT Token for authentication
    private boolean isSuccess;  // ✅ Follows Java naming conventions
    private UserDTO user;  // ✅ Contains user details after authentication
}
