package com.sandip.bus.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {


    @Column(name = "contact", nullable = false)
    private long contact;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "password", nullable = false, length = 12)
    private String password;
    
    @Column(name = "status", nullable = false)
    private char status;

//    @Column(name = "user_name", nullable = false, length = 255, unique = true)
//    private String userName;
}

