package com.sunbeam.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Haa class configuration sathi ahe
public class WebConfig {
    
    @Bean // Haa method ek Spring Bean return karto jo CORS configuration provide karto
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Saglya endpoints sathi CORS enable karto
                        .allowedOrigins("http://localhost:3000") // Frontend sathi allowed origin set karto
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods specify karto
                        .allowedHeaders("*") // Saglya headers allow karto
                        .allowCredentials(true); // Credentials (cookies, authorization headers) allow karto
            }
        };
    }
}
