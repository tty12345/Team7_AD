package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.repo.CarPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class searchController {
    
    @Autowired
    CarPostRepository CPRepo;

    @GetMapping("/listStudents")
    public String listStudents(Model model, HttpSession session) {
			


        return "list_car.html";
    }
}
