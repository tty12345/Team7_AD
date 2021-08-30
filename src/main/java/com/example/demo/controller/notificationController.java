package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.Notifications;
import com.example.demo.service.NotificationService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://team7nodejscarexchange-env.eba-5ce3pmnb.us-east-1.elasticbeanstalk.com/")
@RequestMapping("/notification")
public class notificationController {

	@Autowired
	NotificationService nservice;

	@Autowired
	UserService uservice;

	@GetMapping("/listNotification/{id}")
	public List<Notifications> listNotifications(@PathVariable("id") Integer id) {
		return nservice.findByUserId(id);
	}

}
