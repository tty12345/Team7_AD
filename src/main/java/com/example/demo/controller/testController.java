package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Notifications;
import com.example.demo.domain.User;
import com.example.demo.service.CarPostService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class testController {

    @Autowired
	CarPostService cpservice;
    @Autowired
    UserService uservice;

    @GetMapping("/deletePost/{id}")
	public String deleteCarPost(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cpservice.findCarPostById(id);
        if(carpost.getUsers().size()==0){
            List<User> users=new ArrayList<>();
            carpost.setUsers(users);
        }
		List<User> userlist = carpost.getUsers();

		for (User user : userlist) {
			Notifications notification = new Notifications();
			notification.setType("delete");
			notification.setUser(user);
            notification.setMsg("One of your favourite carposting was deleted");
            if(user.getNotifications().size()==0){
                List<Notifications> notifications=new ArrayList<>();
                user.setNotifications(notifications);
            }
			user.getNotifications().add(notification);
		}

		carpost.setOwner(null);
		cpservice.delete(carpost);
		return "forward:/test/listPost";
	}
    
    @GetMapping("/listPost")
	public String listCarPost(Model model){
		
		model.addAttribute("carpost", cpservice.findAll());
		return "list_car.html";
	}

    @GetMapping("/offer/{id}")
	public String offer(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cpservice.findCarPostById(id);
		model.addAttribute("carpost", carpost);

		// increment number of views for a car
		carpost.setViews(carpost.getViews() + 1);
		cpservice.save(carpost);
		return "detailsPage";
	}
    @GetMapping("/addLike/{id}")
    public String addLike (Model model,@PathVariable("id") int id,HttpSession session){
        CarPosting carposting =cpservice.findCarPostById(id);
        int userId;
        if(session.getAttribute("user")!=null){
            userId=(Integer)session.getAttribute("userId");
            User u= uservice.finduserById(userId);
            if(u.getFavourites().size()==0){
                List<CarPosting> favourites = new ArrayList<>();
                favourites.add(carposting);
                
                u.setFavourites(favourites);
                uservice.save(u);
                List<User> list1 = carposting.getUsers();
                list1.add(u);
                carposting.setUsers(list1);
                cpservice.save(carposting);
                
                
            }
           else{
               u.getFavourites().add(carposting);
               uservice.save(u);
           }
        }
        else if(session.getAttribute("user")==null){
            session.setAttribute("return", "/post/offer/"+id);
            return "forward:/login";
        }
       
        model.addAttribute("carpost", carposting);
        return "detailsPageWithLike.html";
    }

}
