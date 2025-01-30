package com.sunbeam.booking.dto;

import lombok.Data;

/**
 * 📝 LoginDTO - Login Request Data Transfer Object
 * 📌 Login साठी Client कडून येणाऱ्या Data साठी DTO.
 */
@Data
public class LoginDTO {
    private String email;
    private String password;
}
