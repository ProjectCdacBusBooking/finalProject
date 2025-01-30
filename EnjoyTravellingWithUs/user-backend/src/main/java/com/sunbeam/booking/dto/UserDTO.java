package com.sunbeam.booking.dto;

import lombok.Data;

/**
 * 📝 UserDTO - Request Data Transfer Object
 * 📌 Client कडून येणाऱ्या Data साठी DTO वापरतो.
 */
@Data
public class UserDTO {
    private String fullName;
    private String email;
    private String password;
    private String phone;
}
