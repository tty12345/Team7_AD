package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    UserService uservice;

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(int id) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("a0124939w@gmail.com");
            message.setTo(uservice.finduserById(id).getEmail());
            message.setText("Hello " + uservice.finduserById(id).getUsername()
                    + " you have successfully created an account with us!");
            message.setSubject("Welcome to CarExchange");

            System.out.println(uservice.finduserById(id).getEmail());
            System.out.println(uservice.finduserById(id).getUsername());

            mailSender.send(message);
            System.out.println("Email sent");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
