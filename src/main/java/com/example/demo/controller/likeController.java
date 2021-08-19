package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.CarPosting;
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
@RequestMapping("/like")
public class likeController {
    @Autowired
	UserService uservice;
    @Autowired
    CarPostService cpservice;

	@GetMapping("/listFavourites/{id}")
	public String listFavouritesByUser(Model model, @PathVariable("id") Integer id) {

		List<CarPosting> favouriteList = uservice.findFavouritesByUserId(id);

		model.addAttribute("carpost", favouriteList);
		return "list_favourites.html";
	}
    @GetMapping("/listFavourites")
	public String listFavourites(Model model) {

		List<CarPosting> favouriteList = uservice.findAllFavourites();

		model.addAttribute("carpost", favouriteList);
		return "list_favourites.html";
	}
    @GetMapping("/addLike/{id}")
    public String addLike (Model model,@PathVariable("id") int id,HttpSession session){
        CarPosting carposting =cpservice.findCarPostById(id);
        int userId;
        if(session.getAttribute("user")!=null){
            userId=(Integer)session.getAttribute("userId");
            User u= uservice.finduserById(userId);
            if(u.getFavourites()==null){
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
            return "forward:/login";
        }
       
        model.addAttribute("carpost", carposting);
        return "detailsPageWithLike.html";
    }
    @GetMapping("/deleteLike/{id}")
    public String deleteLike(Model model,@PathVariable("id") int id,HttpSession session){
        CarPosting carposting=cpservice.findCarPostById(id);
        int userId;
        if(session.getAttribute("user")!=null){
            userId=(Integer)session.getAttribute("userId");
            User u= uservice.finduserById(userId);
            u.getFavourites().remove(carposting);
        }
        else if(session.getAttribute("user")==null){
            return "forward:/login";
        }
        model.addAttribute("carpost",carposting);
        return "detailsPage.html";




    }

}
