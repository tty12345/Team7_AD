package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public boolean checkSession(HttpSession session, String s_name) {
		if (session.getAttribute(s_name) != null )
			return true;
		else 
			return false;
	}

    @Override
    public List<User> listUser() {
        
        return urepo.findAll();
    }
    
}
