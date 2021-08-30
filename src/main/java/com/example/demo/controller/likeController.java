package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.User;
import com.example.demo.service.CarPostService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://team7adreactclientcarexchange-env.eba-mpprj4gb.us-east-1.elasticbeanstalk.com/")
@RequestMapping("/like")
public class likeController {
    @Autowired
	UserService uservice;
    @Autowired
    CarPostService cpservice;

    @PostMapping("/addLike/{id}")
    public ResponseEntity<HttpStatus> addLike (@PathVariable("id") int id,@RequestBody User user){
        //find the current car post from the database
        CarPosting carposting =cpservice.findCarPostById(id);

        int likeCount = carposting.getLikeCount() + 1;
        carposting.setLikeCount(likeCount);

        int userId= user.getUserId();
        User u= uservice.finduserById(userId);

        u.getFavourites().add(carposting);
        uservice.save(u);
        List<User> list1 = carposting.getUsers();
        list1.add(u);
        carposting.setUsers(list1);
        cpservice.save(carposting);
              
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deleteLike/{id}")
    public ResponseEntity<HttpStatus> deleteLike(@PathVariable("id") int id,@RequestBody User user){

        CarPosting carposting=cpservice.findCarPostById(id);

        carposting.setLikeCount(carposting.getLikeCount() - 1);

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
