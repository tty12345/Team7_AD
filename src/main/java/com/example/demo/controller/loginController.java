package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import com.example.demo.domain.*;
import com.example.demo.repo.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class loginController {
		
	@Autowired
	CarPostService cpservice;
	
	@Autowired
	OfferRepository orepo;
	
	@Autowired
	UserService uservice;
	
	SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
	
	@GetMapping("/getOne/{id}")
    public CarPosting getCar(@PathVariable("id") Integer id){
		return cpservice.findCarPostById(id);
	}
	@PostMapping("/saveOffer")
    public void createOffer(@RequestBody Offer offer){
		orepo.save(new Offer(offer.getOffer()));
    }
	
	@PostMapping("/authenticate")
	public ResponseEntity<HttpStatus> authenticateUser(@RequestBody User user, HttpSession session){
		if (authenticateUser(user)){
			User loggeduser = uservice.findUserByUsername(user.getUsername());
			
			if(loggeduser.getRole() == UserType.USER){
				session.setAttribute("user",loggeduser);
				session.setAttribute("userId", loggeduser.getUserId());
			}
				
			else{
				session.setAttribute("admin", loggeduser);
				session.setAttribute("userId", loggeduser.getUserId());
			}
				
			// if(session.getAttribute("return")!=null)
			// {
			// 	String returnLink = (String) session.getAttribute("return");
			// 	session.removeAttribute("return");
			// 	return "redirect:"+returnLink;
			// }
			// else
			// 	return "index";
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	private boolean authenticateUser(User user) {
		User username_object = uservice.findUserByUsername(user.getUsername());
		if (username_object == null)
			return false;
		else 
			return (sCryptPasswordEncoder.matches(user.getPassword(),username_object.getPassword()));
	}
	
	@GetMapping("/checkloggedin")
	@ResponseBody
	public String checklogin(HttpSession session){
		if (session.getAttribute("user") != null || session.getAttribute("admin") != null)
			return "true";
		else
			return "false";
	}
	
	// unfinished in react part
	@PostMapping("/logout")
	public ResponseEntity<HttpStatus> logout(HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("admin");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
		try {
			String Pass = sCryptPasswordEncoder.encode(user.getPassword());
			uservice.save(new User(user.getUsername(), Pass, UserType.USER));
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}

