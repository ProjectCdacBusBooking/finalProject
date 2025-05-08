package com.sunbeam.booking.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String msg;  // ✅ Response message
    private LocalDateTime datetime = LocalDateTime.now();  // ✅ Default timestamp

    public ApiResponse(String msg) {
        this.msg = msg;
        this.datetime = LocalDateTime.now();
    }
}
