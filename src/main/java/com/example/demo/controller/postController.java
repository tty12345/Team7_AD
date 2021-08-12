package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Notifications;
import com.example.demo.domain.User;
import com.example.demo.repo.CarPostRepository;
import com.example.demo.repo.NotificationRepository;
import com.example.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class postController {

    @Autowired
    CarPostRepository cprepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	NotificationRepository nrepo;
	
    
	@GetMapping("/addPost")
	public String showForm(Model model) {
		CarPosting carpost = new CarPosting();
		model.addAttribute("carpost", carpost);
		return "car_post_form";
	}
	
	//Edit car post's detail
	@GetMapping("/editPost/{id}")
	  public String editCarPost(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cprepo.findCarPostById(id);
		model.addAttribute("carpost", carpost);
		return "car_post_form";
	}
    
    @GetMapping("/deletePost/{id}")
    public String deleteCarPost(Model model, @PathVariable("id") Integer id) {
      CarPosting carpost = cprepo.findCarPostById(id);
	  List<User> users =carpost.getUsers();
		
	  for (User user : users) {
		Notifications notification =new Notifications();
		notification.setType("delete");
		notification.setUser(user);
		user.notifications.add(notification);
	  }
	 
	  cprepo.delete(carpost);
      return "forward:/post/listPost";
    }
    
     
	//Save car's details after editing
	@GetMapping("/savePost")
	public String saveCarPost(@ModelAttribute("carpost") @Valid CarPosting carpost, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "car_post_form";
		}

		// if(carpost.getUsers() == null)
		// {
		// 	//add code to set user as whoever is logged in 
		// 	User user = urepo.finduserById(1);
		// 	List<CarPosting> newpost = new ArrayList<CarPosting>();
		// 	newpost.add(carpost);
		// 	user.setPostings(newpost);
		// 	urepo.save(user);
		// 	carpost.getUsers().add(user);
		// }

		cprepo.save(carpost);
		return "forward:/post/listPost";
	}

    //show all cars
    @GetMapping("/listPost")
	public String listCarPost(Model model) {
		model.addAttribute("carpost", cprepo.findAll());
		return "list_car.html";
	}
}
