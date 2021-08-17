package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {

    public User findUserByUsername(String username);
    public void save(User user);
    public User finduserById(int id);
    
}
