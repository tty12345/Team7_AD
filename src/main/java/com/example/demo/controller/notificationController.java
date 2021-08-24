package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.Notifications;
import com.example.demo.domain.User;
import com.example.demo.service.NotificationService;
import com.example.demo.service.UserService;

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
	@Autowired
	UserService uservice;
	@GetMapping("/listNotification/{id}")
	public String listNotifications(Model model, @PathVariable("id") Integer id,HttpSession session) {

		int userId;
        if(session.getAttribute("user")!=null){
            userId=(Integer)session.getAttribute("userId");
            User u= uservice.finduserById(userId);
		
            if(u.getNotifications().size()==0){
                List<Notifications> notifications = new ArrayList<>();
                
                u.setNotifications(notifications);
                uservice.save(u);
				for(Notifications ntf : notifications){
					ntf.setUser(u);
					nservice.save(ntf);
				}
                
            }
           
			// List<Notifications> notifications = uservice.findNotificationsByUserId(userId);
			// model.addAttribute("notifications", notifications);
           
        }
        else if(session.getAttribute("user")==null){
            session.setAttribute("return", "/notification/listNotification/"+id);
            return "forward:/login";
        }
       
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
	@GetMapping("/deleteNotification/{id}")
    public String deleteNotification(Model model, @PathVariable("id") Integer id) {
      Notifications notification = nservice.findNotificationById(id);
	  nservice.delete(notification);
      return "forward:/notification/listNotification";
    }
    

}
