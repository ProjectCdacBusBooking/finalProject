

//package com.sunbeam.booking.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
///**
// *  User Entity - Database साठी Table Mapping
// *  User Authentication साठी Entity Class
// */
//
//import jakarta.validation.constraints.NotEmpty;
//
//@Data
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotEmpty(message = "Full name is required")
//    @Column(name = "full_name", nullable = false)
//    private String fullName;
//
//    @NotEmpty(message = "Email is required")
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @NotEmpty(message = "Password is required")
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @NotEmpty(message = "Phone number is required")
//    @Column(name = "phone", nullable = false)
//    private String phone;
//
//    // Getters and Setters
//}


package com.sunbeam.booking.entity;

import java.util.List;

import javax.management.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;
}
