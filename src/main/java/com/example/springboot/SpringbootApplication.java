package com.example.springboot;

import com.example.springboot.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

    @Autowired
    private EmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
