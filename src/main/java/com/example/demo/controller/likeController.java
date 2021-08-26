package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.User;
import com.example.demo.service.CarPostService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/like")
public class likeController {
    @Autowired
	UserService uservice;
    @Autowired
    CarPostService cpservice;

	@GetMapping("/listFavourites/{id}")
	public String listFavouritesByUser(Model model, @PathVariable("id") Integer id,HttpSession session) {

		List<CarPosting> favouriteList = uservice.findFavouritesByUserId(id);
        session.setAttribute("returnFromLike", "/like/listFavourites/"+id);

		model.addAttribute("carpost", favouriteList);
		return "list_favourites.html";
	}
    @GetMapping("/listFavourites")
	public String listFavourites(Model model) {

		List<CarPosting> favouriteList = uservice.findAllFavourites();

		model.addAttribute("carpost", favouriteList);
		return "list_favourites.html";
	}
    // @GetMapping("/addLike/{id}")
    // public String addLike (Model model,@PathVariable("id") int id,HttpSession session){
    //     //find the current car post from the database
    //     CarPosting carposting =cpservice.findCarPostById(id);
    //     int userId;
    //     if(session.getAttribute("user")!=null){
    //         userId=(Integer)session.getAttribute("userId");
    //         User u= uservice.finduserById(userId);
    //         if(u.getFavourites().size()==0){
    //             List<CarPosting> favourites = new ArrayList<>();
    //             favourites.add(carposting);
                
    //             u.setFavourites(favourites);
    //             uservice.save(u);
    //             List<User> list1 = carposting.getUsers();
    //             list1.add(u);
    //             carposting.setUsers(list1);
    //             cpservice.save(carposting);
                
                
    //         }
    //        else{
    //            u.getFavourites().add(carposting);
    //            uservice.save(u);
    //        }
    //     }
    //     else if(session.getAttribute("user")==null){
    //         session.setAttribute("return", "/post/offer/"+id);
    //         return "forward:/login";
    //     }
       
    //     model.addAttribute("carpost", carposting);
    //     return "detailsPageWithLike.html";
    // }

    @PostMapping("/addLike/{id}")
    public ResponseEntity<HttpStatus> addLike (@PathVariable("id") int id,@RequestBody User user){
        //find the current car post from the database
        CarPosting carposting =cpservice.findCarPostById(id);

        int userId= user.getUserId();
        User u= uservice.finduserById(userId);

        //if currently there is no items in favourites
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
        //if currently there is items in favourites
        else{
            //get current favourite, add new favourite, save
            u.getFavourites().add(carposting);
            uservice.save(u);
           }
        
        // dun need to chekc log in here any more. Check login done on react side
        // else
        // {
        //     // let session remember what is the last post they view, but can implement on react side
        //     // session.setAttribute("return", "/post/offer/"+id);
        //     return "forward:/login";
        // }
       
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // @GetMapping("/deleteLike/{id}")
    // public String deleteLike(Model model,@PathVariable("id") int id,HttpSession session){
    //     CarPosting carposting=cpservice.findCarPostById(id);
    //     int userId;
    //     if(session.getAttribute("user")!=null){
    //         userId=(Integer)session.getAttribute("userId");
    //         User u= uservice.finduserById(userId);
    //         u.getFavourites().remove(carposting);
    //         if(session.getAttribute("returnFromLike")!=null){
    //             String returnLike = (String) session.getAttribute("returnFromLike");
	// 			session.removeAttribute("returnFromLike");
	// 			return "redirect:"+returnLike;

    //         }
    //         else{
    //             model.addAttribute("carpost",carposting);
    //             return "detailsPage.html";

    //         }

    //     }
    //     else
    //         return "forward:/login";
    // }

    @PostMapping("/deleteLike/{id}")
    public ResponseEntity<HttpStatus> deleteLike(@PathVariable("id") int id,@RequestBody User user){

        CarPosting carposting=cpservice.findCarPostById(id);

        int userId = user.getUserId();
        User u= uservice.finduserById(userId);
        List<CarPosting> newLikesList = u.getFavourites();
        newLikesList.remove(carposting);
        u.setFavourites(newLikesList);
        uservice.save(u);

        List<User> currentLikers = carposting.getUsers();
        currentLikers.remove(u);
        carposting.setUsers(currentLikers);
        cpservice.save(carposting);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    @PostMapping("/checkLike/{id}")
    public ResponseEntity<Integer> checkLike(@PathVariable("id") int id,@RequestBody User user){

        //increment view count of current carposting 
        CarPosting currentCar = cpservice.findCarPostById(id); 
        currentCar.setViews(currentCar.getViews() + 1); 
        cpservice.save(currentCar);

        int userId = user.getUserId();
        User u= uservice.finduserById(userId);
        List<CarPosting> currentLikes = u.getFavourites();
        if(currentLikes.size()>0)
            for(CarPosting carpost:currentLikes)
                {
                    if(carpost.getPostId() == id){
                        return new ResponseEntity<>(1,HttpStatus.OK);}
                }
        //if users liked nthing before
        else
            return new ResponseEntity<>(-1,HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(-1,HttpStatus.NO_CONTENT);
    }

}
