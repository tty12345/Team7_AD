package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.Notifications;
import com.example.demo.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notification")
public class notificationController {
	@Autowired
	NotificationService nservice;
	@GetMapping("/listNotification/{id}")
	public String listNotifications(Model model, @PathVariable("id") Integer id) {

		List<Notifications> notificationsList = nservice.findByUserId(id);

		model.addAttribute("notifications", notificationsList);
		return "list_notifications.html";
	}

	// @GetMapping("/listNotification")
	// public String listNotifications(Model model) {
	// 	model.addAttribute("notifications", nservice.findAll());
	// 	return "list_notifications.html";
	// }

	@GetMapping("/listNotification")
	public List<Notifications> listNotifications() {

		System.out.println(nservice.findAll());
		return nservice.findAll();
	}
	

}
