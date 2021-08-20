package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Notifications;
import com.example.demo.domain.Offer;
import com.example.demo.domain.Preference;
import com.example.demo.domain.User;
import com.example.demo.service.CarPostService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.OfferService;
import com.example.demo.service.PreferenceService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class postController {

	@Autowired
	CarPostService cpservice;
	@Autowired
	UserService uservice;
	@Autowired
	OfferService oservice;
	@Autowired
	NotificationService nservice;
	@Autowired
	private PreferenceService prfservice;

	@GetMapping("/addPost")
	public String showForm(Model model, HttpSession session) {
		if (session.getAttribute("seller") == null)
			return "forward:/login";
		else if (session.getAttribute("buyer") != null)
			return "index";

		CarPosting carpost = new CarPosting();
		model.addAttribute("carpost", carpost);
		return "car_post_form";
	}

	// Edit car post's detail
	@GetMapping("/editPost/{id}")
	public String editCarPost(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cpservice.findCarPostById(id);
		model.addAttribute("carpost", carpost);
		return "car_post_form";
	}

	@GetMapping("/deletePost/{id}")
	public String deleteCarPost(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cpservice.findCarPostById(id);
		List<User> users = carpost.getUsers();

		for (User user : users) {
			Notifications notification = new Notifications();
			notification.setType("delete");
			notification.setUser(user);
			user.notifications.add(notification);
		}

		carpost.setOwner(null);
		cpservice.delete(carpost);
		return "forward:/post/listPost";
	}

	// Save car's details
	@PostMapping("/savePost")
	public ResponseEntity<CarPosting> saveCarPost(@RequestBody CarPosting carpost, Model model) {
		// checks if this is a new post
		// if (carpost.getUsers() == null) {
		// // add code to set user as whoever is logged in

		// // List<Preference> preflist=(ArrayList<Preference>) prfservice.listPref();
		// // for (Preference preference : preflist) {
		// // if(preference.getBrand()==carpost.getBrand() &&
		// // preference.getCategory()==carpost.getCategory() &&
		// // preference.getEngineCapacityMax()<=carpost.getEngineCapacity() &&
		// // preference.getEngineCapacityMin()>=carpost.getEngineCapacity()
		// // && preference.getHighestPrice()<=carpost.getPrice()){
		// // return "forward:/post/notification";
		// // }
		// // }
		// }
		try {
			User user = uservice.finduserById(1);

			CarPosting newCarPosting = new CarPosting(carpost.getPrice(), carpost.getDescription(), carpost.getBrand(),
					carpost.getEngineCapacity(), carpost.getRegisteredDate(), carpost.getMileage(),
					carpost.getCategory(), carpost.getPhotoUrl(), user);
			cpservice.save(newCarPosting);

			List<CarPosting> newpost = new ArrayList<CarPosting>();
			newpost.add(newCarPosting);
			user.setPostings(newpost);
			uservice.save(user);
			List<User> existingList = newCarPosting.getUsers();
			existingList.add(user);
			carpost.setOwner(user);
			cpservice.save(newCarPosting);
			return new ResponseEntity<>(newCarPosting, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

	}

	// show all cars
	@RequestMapping("/listPost")
	public String listCarPost(Model model, @RequestParam Map<String, String> allParams) {

		// on first run, value = to null
		// if serach without any value, the value will be = to "". So during first
		// search, set to ""
		// set brand
		String brand = allParams.get("brand");
		if (brand == "")
			brand = null;
		String description = allParams.get("description");
		if (description == "")
			description = null;

		// set price range
		String priceLabel = allParams.get("price");
		int minPrice = 0;
		int maxPrice = 99999999;
		if (priceLabel != null && priceLabel != "") {
			if (priceLabel.contains("0")) {
				minPrice = 0;
				maxPrice = 50000;
			} else if (priceLabel.contains("1")) {
				minPrice = 50001;
				maxPrice = 100000;
			} else if (priceLabel.contains("2")) {
				minPrice = 100001;
				maxPrice = 150000;
			} else if (priceLabel.contains("3")) {
				minPrice = 150001;
				maxPrice = 99999999;
			}
		}

		// depending on which field is entered, do the corresponding query
		// if all null, display all
		if (brand == null && maxPrice == 0 && description == null)
			model.addAttribute("carpost", cpservice.findAll());

		else
			model.addAttribute("carpost", cpservice.filterAllIgnoreCase(brand, minPrice, maxPrice, description));

		return "list_car";
	}

	@GetMapping("/recommended")
	public String recommendedCars(Model model) {
		Preference pref = prfservice.findprefByuserId(1);
		List<CarPosting> cars = cpservice.findCarPostByPref(pref.getModel(), pref.getBrand());
		model.addAttribute("prefcars", cars);
		return "recommended_cars";
	}

	@GetMapping("/mostViewed")
	public String mostViewed(Model model) {
		List<CarPosting> mostviewed = cpservice.findMostViewedCars();
		model.addAttribute("carpost", mostviewed);
		return "list_car";
	}

	@GetMapping("/viewOwnPost")
	public String viewOwnPost(Model model) {
		User user = uservice.finduserById(1);
		List<CarPosting> ownPostings = cpservice.findCarPostByUserId(user.getUserId());
		model.addAttribute("carpost", ownPostings);
		return "list_seller";
	}

	@GetMapping("/viewOffer/{id}")
	public String viewOffer(Model model, @PathVariable("id") Integer id) {
		List<Offer> offers = oservice.findOffersByCarPostId(id);
		model.addAttribute("carpost", cpservice.findCarPostById(id));
		model.addAttribute("offers", offers);
		return "offerDetails";
	}

	@GetMapping("/offer/{id}")
	public String offer(Model model, @PathVariable("id") Integer id) {
		CarPosting carpost = cpservice.findCarPostById(id);
		model.addAttribute("carpost", carpost);

		// increment number of views for a car
		carpost.setViews(carpost.getViews() + 1);
		cpservice.save(carpost);
		return "detailsPage";
	}

	@PostMapping("/saveOffer/{id}")
	public String leaveOffer(@PathVariable("id") Integer id, @RequestParam("offer") Integer offer) {
		User user1 = uservice.finduserById(1);
		CarPosting carposting1 = cpservice.findCarPostById(id);

		Offer newOffer = new Offer(offer, user1, carposting1);
		oservice.save(newOffer);

		Notifications notification1 = new Notifications("New Offer", user1, "An offer of " + "$" + newOffer.getOffer()
				+ " has been made for your post " + carposting1.getPostId() + "!");
		nservice.save(notification1);

		user1.getNotifications().add(notification1);
		uservice.save(user1);

		return "redirect:/post/listPost";
	}

	// @GetMapping("/populate")
	// public String populateData(){

	// try {
	// BufferedReader bufferedReader = new BufferedReader(
	// new FileReader("demo/src/main/resources/static/cars.csv"));

	// String input;
	// int count = 0;
	// while((input = bufferedReader.readLine()) != null)
	// {
	// count++;
	// }
	// bufferedReader.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

	// return "forward:/post/listPost";
	// }
	@RequestMapping("/notification")
	public String createNotification(Model model) {
		Notifications ntf = new Notifications("New Arrival that matches your preference");
		model.addAttribute("ntf", ntf);
		return "notification";
	}

}
