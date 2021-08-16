package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Preference;
import com.example.demo.service.PreferenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;







@Controller
@RequestMapping("/preference")
public class SetPreferenceController {
	
	@Autowired
	private PreferenceService prfservice;
	
	@Autowired
	public void setPreference(PreferenceService prfservice) {
		this.prfservice=prfservice;
	}
	
	@GetMapping("/setpreference")
	public String setpreference(Model model) {
		Preference pref=new Preference();
		model.addAttribute("pref",pref);
		return "preferenceform.html";
	}
	
	@GetMapping("/save")
	public String savePreference(@ModelAttribute("pref") Preference pref, Model model)
	{
		prfservice.save(pref);
		return "forward:/preference/list"; 
	}
	
	@GetMapping("/list")
	public String listPreference(Model model) {
		//ArrayList<Preference> preflist1=(ArrayList<Preference>) prfservice.list();
		List<Preference> preflist=(ArrayList<Preference>) prfservice.listPref();
		model.addAttribute("preflist",preflist);
		return "preference.html";
	}


}
