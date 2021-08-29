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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class loginController {

	@Autowired
	CarPostService cpservice;

	@Autowired
	OfferService oservice;

	@Autowired
	UserService uservice;

	@Autowired
	EmailService eservice;

	SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();

	@GetMapping("/getOne/{id}")
	public CarPosting getCar(@PathVariable("id") Integer id) {
		return cpservice.findCarPostById(id);
	}

	@PostMapping("/saveOffer")
	public void createOffer(@RequestBody Offer offer) {
		oservice.save(new Offer(offer.getOffer()));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Integer> authenticateUser(@RequestBody User user, HttpSession session) {
		if (authenticateUser(user)) {
			User loggeduser = uservice.findUserByUsername(user.getUsername());

			if (loggeduser.getRole() == UserType.USER)
				session.setAttribute("user", loggeduser.getUserId());

			else
				session.setAttribute("admin", loggeduser.getUserId());

			return new ResponseEntity<>(loggeduser.getUserId(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(-1, HttpStatus.UNAUTHORIZED);
	}

	private boolean authenticateUser(User user) {
		User username_object = uservice.findUserByUsername(user.getUsername());
		if (username_object == null)
			return false;
		else
			return (sCryptPasswordEncoder.matches(user.getPassword(), username_object.getPassword()));
	}

	@GetMapping("/checkloggedin")
	@ResponseBody
	public String checklogin(HttpSession session) {
		if (session.getAttribute("user") != null || session.getAttribute("admin") != null)
			return "true";
		else
			return "false";
	}

	@PostMapping("/logout")
	public ResponseEntity<HttpStatus> logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("admin");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
		signup(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	 }

	@PostMapping("/googlelogin")
	public ResponseEntity<HttpStatus> createGoogleuser(@RequestBody User user){
		User googleUser = uservice.findUserByUsernameAndEmail(user.getUsername(), user.getEmail());
		if ( googleUser == null) {
			signup(user);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void signup(User signupUser){
		String Pass = sCryptPasswordEncoder.encode(signupUser.getPassword());
		User user1 = new User(signupUser.getUsername(), Pass, UserType.USER, signupUser.getEmail());
		uservice.save(user1);

		// sending email for user creation confirmation
		eservice.sendEmail(user1.getUserId());
	}

}
