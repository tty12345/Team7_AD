package com.example.demo.controller;

import java.util.List;

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

	@GetMapping("/listNotification/{id}")
	public String listNotifications(Model model, @PathVariable("id") Integer id) {

		List<Notifications> notificationsList = nrepo.findByUserId(id);

		model.addAttribute("notifications", notificationsList);
		return "list_notifications.html";
	}

	@GetMapping("/listNotification")
	public String listNotifications(Model model) {
		model.addAttribute("notifications", nrepo.findAll());
		return "list_notifications.html";
	}

}