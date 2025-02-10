//package com.sunbeam.booking.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//
//    @Bean
//    protected JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("harshad.sunbeam98@gmail.com");
//        mailSender.setPassword("uxwa hdmk sytn cwqs"); // Use your app password here
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
//}


package com.sunbeam.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Value;
import java.util.Properties;

@Configuration // Haa class configuration sathi ahe
public class MailConfig {

    @Value("${spring.mail.username}") // Application properties madhun mail username gheto
    private String username;

    @Value("${spring.mail.password}") // Application properties madhun mail password gheto
    private String password;

    @Bean // Haa method ek Spring Bean return karto jo JavaMailSender provide karto
    JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Gmail cha SMTP server use karto
        mailSender.setPort(587); // SMTP sathi port 587 set karto
        mailSender.setUsername(username); // SMTP authentication sathi username set karto
        mailSender.setPassword(password); // SMTP authentication sathi password set karto

        // SMTP server sathi properties set karto
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp"); // SMTP protocol use karto
        props.put("mail.smtp.auth", "true"); // SMTP authentication enable karto
        props.put("mail.smtp.starttls.enable", "true"); // TLS enable karto security sathi
        props.put("mail.debug", "true"); // Debugging enable karto mail send hotana logs sathi

        return mailSender; // Configured mail sender return karto
    }
}
