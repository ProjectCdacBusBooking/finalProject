package com.sunbeam.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingConfirmationDTO {
    
    private Long busId;  
    private String status;  // ✅ "CONFIRMED", "FAILED", etc.
    private String seatNumbers;  // ✅ Comma-separated seat numbers
    private String message;  // ✅ Custom message for confirmation or failure

   
    
}
