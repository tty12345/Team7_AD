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

		String hotBrand = preference.getBrand();
		String realBrand = "";
		switch (hotBrand) {
			case "0":
				realBrand = "Audi";
				break;
			case "1":
				realBrand = "Austin";
				break;
			case "2":
				realBrand = "BMW";
				break;
			case "3":
				realBrand = "Citron";
				break;
			case "4":
				realBrand = "Ferrari";
				break;
			case "5":
				realBrand = "Fiat";
				break;
			case "6":
				realBrand = "Honda";
				break;
			case "7":
				realBrand = "Hyundai";
				break;
			case "8":
				realBrand = "Kia";
				break;
			case "9":
				realBrand = "Lexus";
				break;
			case "10":
				realBrand = "Mini";
				break;
			case "11":
				realBrand = "Mercedes-Benz";
				break;
			case "12":
				realBrand = "Mitsubishi";
				break;
			case "13":
				realBrand = "Morris";
				break;
			case "14":
				realBrand = "Nissan";
				break;
			case "15":
				realBrand = "Opel";
				break;
			case "16":
				realBrand = "Peugeot";
				break;
			case "17":
				realBrand = "Porsche";
				break;
			case "18":
				realBrand = "Renault";
				break;
			case "19":
				realBrand = "Subaru";
				break;
			case "20":
				realBrand = "Suzuki";
				break;
			case "21":
				realBrand = "Toyota";
				break;
			case "22":
				realBrand = "Volkswagen";
				break;
			case "23":
				realBrand = "Volvo";
				break;
			default:
				realBrand = "";
		}

		String hotCategory = preference.getCategory();
		String realCategory = "";
		switch (hotCategory) {
			case "1":
				realCategory = "Hatchback";
				break;
			case "2":
				realCategory = "Luxury";
				break;
			case "3":
				realCategory = "MPV";
				break;
			case "4":
				realCategory = "Others";
				break;
			case "5":
				realCategory = "SUV";
				break;
			case "6":
				realCategory = "Sedan";
				break;
			case "7":
				realCategory = "Sports";
				break;
			case "8":
				realCategory = "Stationwagon";
				break;
			case "9":
				realCategory = "Truck";
				break;
			case "10":
				realCategory = "Van";
				break;
			default:
				realCategory = "";
		}

		
		if(currentUser.getPreference()==null){
			Preference newPreference = new Preference(preference.getModel(),realBrand,preference.getLowestPrice(),
			preference.getHighestPrice(),realCategory, preference.getEngineCapacityMin(), 
			preference.getEngineCapacityMax(),preference.getDepreciationMax(),currentUser);
			prfservice.save(newPreference);
		}
		else{
			Preference oldPreference = currentUser.getPreference();
			oldPreference.setModel(preference.getModel());
			oldPreference.setBrand(realBrand);
			oldPreference.setLowestPrice(preference.getLowestPrice());
			oldPreference.setHighestPrice(preference.getHighestPrice());
			oldPreference.setCategory(realCategory);
			oldPreference.setEngineCapacityMin(preference.getEngineCapacityMin());
			oldPreference.setEngineCapacityMax(preference.getEngineCapacityMax());
			oldPreference.setDepreciationMax(preference.getDepreciationMax());
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
