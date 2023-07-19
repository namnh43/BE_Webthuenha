package com.example.springboot;

import com.example.springboot.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringbootApplication {

    @Autowired
    private EmailService emailService;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() {
        String to = "gathidungru@gmail.com";
        String subject = "Test Email";
        String text = "Công ngu.";

        emailService.sendSimpleEmail(to, subject, text);
    }

}
