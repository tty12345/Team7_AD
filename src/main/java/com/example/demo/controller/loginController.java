package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.domain.UserType;
import com.example.demo.repo.UserRepository;

@Controller
public class loginController {
		
	@Autowired
	UserRepository urepo;
		
	SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
	
	@RequestMapping("/login")
	public String loginForm(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "login";
	}
		
	@PostMapping("/authenticate")
	public String authenticate(@ModelAttribute("user")User user, HttpSession session, Model model) {
		if (authenticateUser(user))
		{
			User loggeduser = urepo.findUserByUsername(user.getUsername());
			model.addAttribute("name", loggeduser.getUsername());
			
			if(loggeduser.getRole() == UserType.BUYER)
				session.setAttribute("buyer",loggeduser);
			else
				session.setAttribute("seller", loggeduser);
			
			return "forward:/post/listPost";
		}
		else
			return "login";
	}
		
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("buyer");
		session.removeAttribute("seller");
		return "index";
	}
		
	public boolean authenticateUser(User user) {
		User username_object = urepo.findUserByUsername(user.getUsername());
		if (username_object == null)
			return false;
		else 
			return (sCryptPasswordEncoder.matches(user.getPassword(),username_object.getPassword()));
	}
	
	@RequestMapping("signupform")
	public String showform(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("user")User user, Model model) {
		String pass = sCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(pass);
		urepo.save(user);
		model.addAttribute("user", user);
		return "forward:/authenticate";
	}
}

