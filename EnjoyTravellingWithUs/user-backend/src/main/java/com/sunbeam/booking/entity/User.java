package com.sunbeam.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 *  User Entity - Database साठी Table Mapping
 *  User Authentication साठी Entity Class
 */
@Entity
@Table(name = "users")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;
}
