package com.sunbeam.booking.entity;  
// 📌 he package declaration ahe, jo sangto ki ha class "com.sunbeam.booking.entity" ya package madhye ahe  

import jakarta.persistence.*;  
// 📌 ha import JPA annotations sathi ahe jo Hibernate la entity manage karayla madat karto  

import lombok.*;  
// 📌 ha Lombok cha import ahe jo automatic getter, setter, constructor generate karto  

@Entity  
// 📌 ha annotation sangto ki ha class database madhil ek table la represent karto  

@Table(name = "wallets")  
// 📌 ha annotation database madhil table cha nav specify karto (ya case madhe "wallets")  

@Getter  
@Setter  
// 📌 he Lombok annotations getter ani setter methods automatic generate karto  

@NoArgsConstructor  
// 📌 ha Lombok annotation default constructor generate karto  

@AllArgsConstructor  
// 📌 ha Lombok annotation all-args constructor generate karto (je madhe saglya fields cha constructor asel)  

@ToString(exclude = "user")  
// 📌 ha annotation toString() madhe 'user' field la exclude karto jyane circular reference (infinite loop) ho nar nahi  

public class Wallet extends BaseEntity {  
// 📌 Wallet ha class ahe jo BaseEntity pasun extend hoto (BaseEntity madhe ID, timestamps asel)  

    @OneToOne  
    // 📌 he mapping dakhavtay ki ek user la ekch wallet asnar  

    @JoinColumn(name = "user_id", nullable = false, unique = true)  
    // 📌 "user_id" ha foreign key ahe jo "users" table madhun data gheto  
    // ✅ nullable = false: ya field la null value deta yenar nahi  
    // ✅ unique = true: pratek user sathi ekach wallet asnar  

    private User user;  
    // 📌 ha reference ahe jo user ani wallet la connect karto  

    @Column(nullable = false)  
    // 📌 ha annotation ensure karto ki balance he column database madhe null hou shakat nahi  

    private double balance;  
    // 📌 wallet madhil paise store karayla balance variable  

    /**  
     * ✅ Method to safely update balance  
     */  
    public void updateBalance(double amount) {  
        // 📌 he method wallet madhil balance update karto  
        this.balance = Math.max(0, this.balance + amount);  
        // ✅ Ensures no negative balance  
        // 📌 Math.max(0, ...) he ensure karto ki balance kadich negative hou naye  
    }  
}  
