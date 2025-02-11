package com.sunbeam.booking.controller;  
// 📌 he package declaration ahe, jo sangto ki ha controller "com.sunbeam.booking.controller" ya package madhye ahe  

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
// 📌 SLF4J (Simple Logging Facade for Java) cha use logging sathi karto  
// 📌 ha logs console var print karnyasathi use hota  

import org.springframework.http.ResponseEntity;  
// 📌 ResponseEntity ha HTTP response wrap karnyasathi vaprato  

import org.springframework.web.bind.annotation.*;  
// 📌 REST API madhe HTTP request handle karnyasathi vaparlya janarya annotations cha import  

import com.sunbeam.booking.dto.ApiResponse;  
import com.sunbeam.booking.dto.WalletDTO;  
// 📌 he DTO (Data Transfer Object) classes import kartoy, je request & response data carry kartat  

import com.sunbeam.booking.exceptions.ResourceNotFoundException;  
// 📌 ha custom exception ahe, jo resource sapdla nahi tr throw kela jato  

import com.sunbeam.booking.service.WalletService;  
// 📌 ha service class ahe, jo business logic handle karto  

@RestController  
// 📌 ha annotation sangto ki ha class ek RESTful API controller ahe  

@RequestMapping("/api/wallets")  
// 📌 ya controller madhe "/api/wallets" ha base URL asnar ahe  

@CrossOrigin(origins = "http://localhost:3000")  
// 📌 React frontend sathi CORS enable karto, jevha frontend ani backend veglya ports var astil  

public class WalletController {  
// 📌 he main controller class ahe je wallet-related API manage karto  

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);  
    // 📌 logger instance create karto, jo logs generate karel  

    private final WalletService walletService;  
    // 📌 ha WalletService cha instance ahe jo business logic execute karel  

    public WalletController(WalletService walletService) {  
        this.walletService = walletService;  
        // 📌 ha constructor dependency injection sathi vaprala jato  
    }  

    /**  
     * ✅ **Get Wallet by User ID**  
     */  
    @GetMapping("/user/{userId}")  
    public ResponseEntity<WalletDTO> getWalletByUserId(@PathVariable Long userId) {  
        // 📌 ha method user ID varun wallet fetch karto  
        log.info("📌 Fetching wallet for User ID: {}", userId);  
        // 📌 log madhe user ID print karto  

        WalletDTO walletDTO = walletService.getWalletByUserId(userId);  
        // 📌 wallet details service la call karun ghetlya jatat  

        if (walletDTO == null) {  
            log.warn("⚠️ Wallet not found for User ID: {}", userId);  
            // 📌 jar wallet sapdla nahi tar warning log dakhvato  
            throw new ResourceNotFoundException("Wallet not found for User ID: " + userId);  
            // 📌 resource not found exception throw karto  
        }  

//        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
        return ResponseEntity.ok(walletDTO);  
        // 📌 response madhe wallet details return karto  
    }  

    /**  
     * ✅ **Add Funds to Wallet**  
     */  
    @PostMapping("/add-funds")  
    public ResponseEntity<ApiResponse> addFunds(@RequestParam Long userId, @RequestParam double amount) {  
        // 📌 ha method user ID varun wallet madhe amount add karto  
        log.info("📌 Adding ₹{} to wallet for User ID: {}", amount, userId);  
        // 📌 log madhe user ID ani amount print karto  

        walletService.addFunds(userId, amount);  
        // 📌 service method call karun fund add karto, jar user sapdla nahi tar exception yeil  

        return ResponseEntity.ok(new ApiResponse("✅ Funds added successfully."));  
        // 📌 response return karto, jo success message dakhvato  
    }  

    /**  
     * ✅ **Withdraw Funds from Wallet**  
     */  
    @PostMapping("/withdraw-funds")  
    public ResponseEntity<ApiResponse> withdrawFunds(@RequestParam Long userId, @RequestParam double amount) {  
        // 📌 ha method wallet madhun amount withdraw karto  
        log.info("📌 Withdrawing ₹{} from wallet for User ID: {}", amount, userId);  
        // 📌 log madhe user ID ani amount print karto  

        walletService.withdrawFunds(userId, amount);  
        // 📌 service method call karun funds withdraw karto  

        return ResponseEntity.ok(new ApiResponse("✅ Funds withdrawn successfully."));  
        // 📌 success message response madhe return karto  
    }  

}  
