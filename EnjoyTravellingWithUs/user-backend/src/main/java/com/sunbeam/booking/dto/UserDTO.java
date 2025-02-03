package com.sunbeam.booking.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {
    @NotEmpty(message = "Full name is required")
    private String fullName;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Phone is required")
    private String phone;
}
