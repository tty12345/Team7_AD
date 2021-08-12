package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Preferences;
import com.example.demo.domain.Offer;

import com.example.demo.domain.User;
import com.example.demo.repo.CarPostRepository;
import com.example.demo.repo.OfferRepository;
import com.example.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post")
public class postController {

    @Autowired
    CarPostRepository cprepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	OfferRepository orepo;
    
	@GetMapping("/addPost")
	public String showForm(Model model) {
		CarPosting carpost = new CarPosting();
		model.addAttribute("carpost", carpost);
		return "car_post_form";
	}
	
	//Edit car post's detail
	@GetMapping("/editPost/{id}")
	  public String editCarPost(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cprepo.findCarPostById(id);
		model.addAttribute("carpost", carpost);
		return "car_post_form";
	}
    
    @GetMapping("/deletePost/{id}")
    public String deleteCarPost(Model model, @PathVariable("id") Integer id) {
      CarPosting carpost = cprepo.findCarPostById(id);
      cprepo.delete(carpost);
      return "forward:/post/listPost";
    }
    
     
	//Save car's details after editing
	@GetMapping("/savePost")
	public String saveCarPost(@ModelAttribute("carpost") @Valid CarPosting carpost, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "car_post_form";
		}

		if(carpost.getUser() == null)
		{
			//add code to set user as whoever is logged in 
			User user = urepo.finduserById(1);
			List<CarPosting> newpost = new ArrayList<CarPosting>();
			newpost.add(carpost);
			user.setPostings(newpost);
			urepo.save(user);
			carpost.setUser(user);
		}
		cprepo.save(carpost);
		return "forward:/post/listPost";
	}

    //show all cars
    @RequestMapping("/listPost")
	public String listCarPost(Model model, @RequestParam Map<String,String> allParams) {
		
		// on first run, value = to null
		// if serach without any value, the value will be = to "". So during first search, set to ""
		//set brand
		String brand = allParams.get("brand");
		if (brand == "")
			brand = null;
		String description = allParams.get("description");
		if (description == "")
			description = null;

		//set price range
		String priceLabel = allParams.get("price");
		int minPrice = 0;
		int maxPrice = 99999999;
		if(priceLabel != null && priceLabel != ""){
			if(priceLabel.contains("0")){
				minPrice = 0;
				maxPrice = 50000;
			}
			else if (priceLabel.contains("1")){
				minPrice = 50001;
				maxPrice = 100000;
			}
			else if (priceLabel.contains("2")){
				minPrice = 100001;
				maxPrice = 150000;
			}
			else if (priceLabel.contains("3")){
				minPrice = 150001;
				maxPrice = 99999999;
			}
		}

		// depending on which field is enter, do the corresponding query
		// if all null, display all
		if(brand == null && maxPrice == 0 && description == null)
			model.addAttribute("carpost", cprepo.findAll());
		
		else
			model.addAttribute("carpost", cprepo.filterAllIgnoreCase(brand,minPrice,maxPrice,description));
		return "list_car.html";
	}
    
    @GetMapping("/recommended")
    public String recommendedCars(Model model) {
    	Preferences pref = urepo.findprefByuserId(1);
    	List<CarPosting> cars = cprepo.findCarPostByPref(pref.getModel(), pref.getBrand());
    	model.addAttribute("prefcars", cars);
    	return "recommended_cars";
    }
    
    @GetMapping("/mostViewed")
    public String mostViewed() {
    	return "forward:/post/listPost";
    }

	@GetMapping("/offer/{id}")
    public String offer(Model model, @PathVariable("id") Integer id) {
      CarPosting carpost = cprepo.findCarPostById(id);
      model.addAttribute("carpost", carpost);
      return "detailsPage";
    }

	@PostMapping("/saveOffer/{id}")
    public String leaveOffer(@PathVariable("id") Integer id, @RequestParam("offer") Integer offer) {
		
		Offer newOffer = new Offer(offer, urepo.finduserById(1), cprepo.findCarPostById(id));
		orepo.save(newOffer);
		return "redirect:/post/listPost";
    }



//	@GetMapping("/populate")
//	public String populateData(){
//
//		try {
//            BufferedReader bufferedReader = new BufferedReader(
//                    new FileReader("demo/src/main/resources/static/cars.csv"));
//
//					String input;
//					int count = 0;
//					while((input = bufferedReader.readLine()) != null)
//					{
//						count++;
//					}
//			bufferedReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//		return "forward:/post/listPost";
//	}

}
