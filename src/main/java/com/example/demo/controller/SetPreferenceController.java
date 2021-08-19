package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.Preference;
import com.example.demo.domain.User;
import com.example.demo.repo.PreferenceRepository;
import com.example.demo.service.PreferenceService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;







@Controller
@RequestMapping("/preference")
public class SetPreferenceController {
	
	@Autowired
	private PreferenceService prfservice;

	@Autowired
	private UserService uservice;
	
	@Autowired
	public void setPreference(PreferenceService prfservice) {
		this.prfservice=prfservice;
	}

	@Autowired
	public void setUserPref(UserService uservice) {
		this.uservice=uservice;
	}
	
	@RequestMapping(value="/setpreference/{userId}")
	public String setpreference(Model model, HttpSession session,@PathVariable("userId") Integer userId) {
		// if (!uservice.checkSession(session, "user")){
		// 	User user=new User();
		// 	model.addAttribute("user", user);
		// 	return "login";

		// }
			  
		User user = (User)session.getAttribute("user");
		if(user.getPreference()!=null){
			return "forward:/preference/viewPref" ;
		}
		else{
			Preference pref=new Preference();
			pref.setUser(user);
			user.setPreference(pref);
			model.addAttribute("pref",pref);
		//model.addAttribute("user", user);
			return "preferenceform";

		}
		//model.addAttribute("user", user);
		//User user=uservice.finduserById(userId);
		
		
		
	}
	
	@GetMapping("/save")
	public String savePreference(@ModelAttribute("pref") Preference pref, Model model,HttpSession session)
	{
		prfservice.save(pref);
		User user=(User) session.getAttribute("user");
		user.setPreference(pref);
		return "forward:/preference/viewPref"; 
	}
	
	@RequestMapping("/list")
	public String listPreference(Model model) {
		//ArrayList<Preference> preflist1=(ArrayList<Preference>) prfservice.list();
		List<Preference> preflist=(ArrayList<Preference>) prfservice.listPref();
		model.addAttribute("preflist",preflist);
		return "preference.html";
	}

	@GetMapping("/edit/{id}")
	  public String showEditForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("pref", prfservice.findPreferenceById(id));
		return "preferenceform";
	  }

	@RequestMapping("/viewPref")
	public String viewPreference(Model model,HttpSession session) {
		//ArrayList<Preference> preflist1=(ArrayList<Preference>) prfservice.list();
		User user = (User)session.getAttribute("user");
		Preference userPref=user.getPreference();
		
		model.addAttribute("pref",userPref);
		return "view_preference";
	}

	


}
