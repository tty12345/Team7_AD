package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.CarImage;
import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Notifications;
import com.example.demo.domain.Offer;
import com.example.demo.domain.Preference;
import com.example.demo.domain.SearchObject;
import com.example.demo.domain.User;
import com.example.demo.repo.CarImageRepository;
import com.example.demo.repo.CarPostRepository;
import com.example.demo.repo.OfferRepository;
import com.example.demo.service.CarPostService;
import com.example.demo.service.NotificationService;
import com.example.demo.service.OfferService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
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
	@Autowired
	private OfferRepository orepo;
	@Autowired
	CarImageRepository cirepo;
	@Autowired
	CarPostRepository cprepo;

	@Autowired
	public void setPrefService(PreferenceService prfservice) {
		this.prfservice=prfservice;
	}



	@GetMapping("/getOne/{id}")
    public CarPosting getCar(@PathVariable("id") Integer id){
		return cpservice.findCarPostById(id);
	}

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

	// Save car's details after editing
	@GetMapping("/savePost")
	public String saveCarPost(@ModelAttribute("carpost") @Valid CarPosting carpost, BindingResult bindingResult,
			Model model, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "car_post_form";
		}

		// checks if this is a new post
		if (carpost.getUsers() == null) {
			// add code to set user as whoever is logged in
			//User userPref=(User) session.getAttribute("user");
			User user = uservice.finduserById(1);
			List<CarPosting> newpost = new ArrayList<CarPosting>();
			newpost.add(carpost);
			user.setPostings(newpost);
			//userPref.setPostings(newpost);
			uservice.save(user);
			//uservice.save(userPref);
			carpost.getUsers().add(user);
			//carpost.getUsers().add(userPref);
			carpost.setOwner(user);
			//carpost.setOwner(userPref);
			Preference preference=user.getPreference();
				if(preference.getBrand()==carpost.getBrand() && preference.getCategory()==carpost.getCategory() &&
				preference.getEngineCapacityMax()<=carpost.getEngineCapacity() && preference.getEngineCapacityMin()>=carpost.getEngineCapacity()
				&& preference.getHighestPrice()<=carpost.getPrice()){
					Notifications ntf=new Notifications("New Arrival", user, "A new arrival that matches your preference is  "+carpost.getPostId());
					nservice.save(ntf);

					user.getNotifications().add(ntf);
					uservice.save(user);
					//model.addAttribute("ntf", ntf);
					//return "notification";
				}
			}
	// Save car's details
	@PostMapping("/savePost/{id}")
	public ResponseEntity<CarPosting> saveCarPost(@RequestBody CarPosting carpost, Model model, @PathVariable("id") Integer imgId) {

		try {
			//find the current logged in
			User user = uservice.finduserById(1);

			//Instatiate a new carpost from the object received from client side
			CarPosting newCarPosting = new CarPosting(carpost.getPrice(), carpost.getDescription(), carpost.getBrand(),
					carpost.getEngineCapacity(), carpost.getRegisteredDate(), carpost.getMileage(),
					carpost.getCategory(), carpost.getPhotoUrl(), user);

			//set this post to the user
			List<CarPosting> newpostList = new ArrayList<CarPosting>();
			newpostList.add(newCarPosting);
			user.setPostings(newpostList);
			uservice.save(user);
			List<User> existingList = newCarPosting.getUsers();
			existingList.add(user);
			carpost.setOwner(user);

			//set car image to the post
			CarImage img1 = cirepo.findByImageId(imgId);
			carpost.setCarPostImage(img1);
			CarPosting newcarPosting2 = cprepo.save(carpost);
			img1.setCarpost(carpost);
			cirepo.save(img1);

			return new ResponseEntity<>(newcarPosting2, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@PostMapping("/listPost")
	public List<CarPosting> listCarPost(@RequestBody SearchObject searchobject) {


		String brand = searchobject.getBrand();
		String priceLabel = searchobject.getPrice();
		String description = searchobject.getDescription();
		
		//set brand
		if (searchobject.getBrand() == "")
			brand = null;

		//set description
		if (description == "")
			description = null;

		// set price range
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
			return cpservice.findAll();

		else
			return cpservice.filterAllIgnoreCase(brand, minPrice, maxPrice, description);
	}

	@GetMapping("/listPost2")
	public List<CarPosting> listCarPost(){
		return cpservice.findAll();
	}

	@GetMapping("/recommended")
	public String recommendedCars(Model model, HttpSession session ) {
		User user=(User) session.getAttribute("user");
		Preference pref = prfservice.findprefByuserId(user.getUserId());
		//List<CarPosting> cars = cpservice.findCarPostByPref(pref.getModel(), pref.getBrand());
		List<CarPosting> cars=cpservice.findCarPostsByPreferences(pref.getModel(), pref.getBrand(), pref.getEngineCapacityMin(), pref.getCategory());
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
	

	@PostMapping("/saveOffer")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer){
        try {
            Offer u = orepo.save(new Offer(offer.getOffer()));
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }



    }

	@PostMapping("/saveImage")
	public ResponseEntity<Integer> saveImage (@RequestParam("photoParam") MultipartFile file, HttpSession session){
		
		CarImage currentCarImage = new CarImage();
		try{
		currentCarImage.setCarpostImage(file.getBytes());
		currentCarImage.setName(file.getName());
		currentCarImage.setType(file.getContentType());
		}

		catch(Exception e){
			System.out.println(e);
		};
		CarImage savedImage = cirepo.save(currentCarImage);
		return new ResponseEntity<>(savedImage.getImageId(), HttpStatus.CREATED);

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

