package com.example.demo.controller;

import com.example.demo.domain.Notifications;
import org.springframework.stereotype.Controller;

import com.example.demo.repo.NotificationRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notification")
public class notificationController {
    @Autowired
	NotificationRepository nrepo;
    @GetMapping("/showNotification")
	public String showNotification(Model model) {
		Notifications notification = new Notifications();
		model.addAttribute("Notification", notification);
		return "notification_list";
	}
    @GetMapping("/listNotification/{id}")
	public String listNotifications(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("notifications", nrepo.findByUserId(id));
		return "list_notifications.html";
	}

}
