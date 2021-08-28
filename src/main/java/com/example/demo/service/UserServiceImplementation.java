package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Notifications;
import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository urepo;

    @Override
    public User findUserByUsername(String username) {
        return urepo.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        urepo.save(user);
    }

    @Override
    public User finduserById(int id) {
        return urepo.finduserById(id);
    }
    @Override
    public List<CarPosting> findFavouritesByUserId(int id){
        return urepo.findFavouritesByUserId(id);
    }
    @Override
    public List<CarPosting> findAllFavourites(){
        return urepo.findAllFavourites();
    }
    @Override
    public List<Notifications> findNotificationsByUserId(int id){
        return urepo.findNotificationsByUserId(id);
    }

    @Override
    public List<User> findAll() {
        return urepo.findAll();
    }

    // @Override
    // public List<CarPosting> findAllFavourites() {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    @Override
    public User findUserByUsernameAndEmail(String username, String email) {
        return urepo.findUserByUsernameAndEmail(username, email);
    }

    
}
