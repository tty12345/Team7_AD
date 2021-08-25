package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Preference;
import com.example.demo.domain.User;
import com.example.demo.service.PreferenceService;
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
@RequestMapping("/preference")
public class SetPreferenceController {
	
	@Autowired
	private PreferenceService prfservice;
	@Autowired
	public UserService uservice;
	
	@Autowired
	public void setPreference(PreferenceService prfservice) {
		this.prfservice=prfservice;
	}
	
	// @GetMapping("/setpreference")
	// public String setpreference(Model model) {
	// 	Preference pref=new Preference();
	// 	model.addAttribute("pref",pref);
	// 	return "preferenceform.html";
	// }
	
	// @GetMapping("/save")
	// public String savePreference(@ModelAttribute("pref") Preference pref, Model model)
	// {
	// 	prfservice.save(pref);
	// 	return "forward:/preference/list"; 
	// }

	@PostMapping("/save")
	public ResponseEntity<HttpStatus> savePreference(@RequestBody Preference preference ){
		
		User currentUser = uservice.finduserById(preference.getUserId());
		
		if(currentUser.getPreference()==null){
			Preference newPreference = new Preference (preference.getModel(),preference.getBrand(),preference.getLowestPrice(),
			preference.getHighestPrice(),preference.getCategory(), preference.getEngineCapacityMin(), 
			preference.getEngineCapacityMax(),currentUser);
			prfservice.save(newPreference);
		}
		else{
			Preference oldPreference = currentUser.getPreference();
			oldPreference.setModel(preference.getModel());
			oldPreference.setBrand(preference.getBrand());
			oldPreference.setLowestPrice(preference.getLowestPrice());
			oldPreference.setHighestPrice(preference.getHighestPrice());
			oldPreference.setCategory(preference.getCategory());
			oldPreference.setEngineCapacityMin(preference.getEngineCapacityMin());
			oldPreference.setEngineCapacityMax(preference.getEngineCapacityMax());
			prfservice.save(oldPreference);

		}

	

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/checkPreference/{id}")
	public ResponseEntity<Preference> checkPreference(@PathVariable("id") Integer userId){
		
		User currentUser = uservice.finduserById(userId);
		
		if(currentUser.getPreference()==null){
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		else{
			return new ResponseEntity<>(currentUser.getPreference(),HttpStatus.OK);

		}
	}
	
	@GetMapping("/list")
	public String listPreference(Model model) {
		//ArrayList<Preference> preflist1=(ArrayList<Preference>) prfservice.list();
		List<Preference> preflist=(ArrayList<Preference>) prfservice.listPref();
		model.addAttribute("preflist",preflist);
		return "preference.html";
	}


}
