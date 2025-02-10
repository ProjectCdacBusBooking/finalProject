package com.sunbeam.booking.dto;  
// 📌 he package declaration ahe, jo sangto ki ha class "com.sunbeam.booking.dto" ya package madhye ahe  

import java.time.LocalDateTime;  
// 📌 LocalDateTime ha Java 8 cha class ahe, jo date ani time store karnyasathi vaprato  

import lombok.AllArgsConstructor;  
import lombok.Data;  
import lombok.NoArgsConstructor;  
// 📌 Lombok cha use kela jato getter, setter, constructor, ani toString() methods automatic generate karnyasathi  

@Data  
// 📌 ha Lombok annotation ahe jo getter, setter, equals(), hashCode(), toString() methods auto-generate karto  

@NoArgsConstructor  
// 📌 ha Lombok annotation default constructor generate karto  

@AllArgsConstructor  
// 📌 ha Lombok annotation all-args constructor generate karto (je madhe saglya fields cha constructor asel)  

public class ApiResponse {  
// 📌 ha class API response sathi vaparla jato jo message ani timestamp send karto  

    private String msg;  // ✅ Response message  
    // 📌 ha response madhe dakhavanyasathi ek message store karto  

    private LocalDateTime datetime = LocalDateTime.now();  
    // 📌 ha field response timestamp store karto, ani default value mhanun system time vaparto  

    public ApiResponse(String msg) {  
        // 📌 ha constructor message gheto, pan datetime default system timestamp thevto  
        this.msg = msg;  
        this.datetime = LocalDateTime.now();  
    }  
}  
