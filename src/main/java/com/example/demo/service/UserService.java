package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Notifications;
import com.example.demo.domain.User;

public interface UserService {

    public User findUserByUsername(String username);
    public void save(User user);
    public User finduserById(int id);
    public List<CarPosting> findFavouritesByUserId(int id);
    public List<Notifications> findNotificationsByUserId(int id);
    public List<CarPosting> findAllFavourites();
    public List<User> findAll();
    public User findUserByUsernameAndEmail(String username, String email);
}
