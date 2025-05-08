package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
<<<<<<< HEAD

    @NotEmpty(message = "Username is required")
    private String username;

//    @NotEmpty(message = "Password is required")
//    private String password;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
=======
    private String firstName;
    private String lastName;
>>>>>>> 4592f26860dd1612aabb10cfb194f28a38b54c75
    private String email;
    private long contact;
    private String password;
}
