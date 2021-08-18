package com.example.demo.controller;

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

		model.addAttribute("favouritesByUser", favouriteList);
		return "list_favourites.html";
	}
    @GetMapping("/addLike/{id}")
    public String addLike (Model model,@PathVariable("id") int id,HttpSession session){
        CarPosting carposting =cpservice.findCarPostById(id);
        int userId=0;
        if(session.getAttribute("buyer")!=null){
            userId=(Integer)session.getAttribute("userId");
        }
        else if(session.getAttribute("buyer")==null){
            return "forward:/login";
        }
        User u= uservice.finduserById(userId);
        u.getFavourites().add(carposting);
        
        model.addAttribute("carposting", carposting);
        return "detailsPageWithLike.html";
    }
    @GetMapping("/deleteLike/{id}")
    public String deleteLike(Model model,@PathVariable("id") int id,HttpSession session){
        CarPosting carposting=cpservice.findCarPostById(id);
        int userId=0;
        if(session.getAttribute("buyer")!=null){
            userId=(Integer)session.getAttribute("userId");
        }
        else if(session.getAttribute("buyer")==null){
            return "forward:/login";
            }
        User u= uservice.finduserById(userId);
        u.getFavourites().remove(carposting);

        model.addAttribute("carposting",carposting);
        return "detailsPage.html";




    }

}
