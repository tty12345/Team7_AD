package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.domain.*;
import com.example.demo.repo.CarImageRepository;
import com.example.demo.repo.CarPostRepository;
import com.example.demo.repo.OfferRepository;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin(origins = "http://team7adreactclientcarexchange-env.eba-mpprj4gb.us-east-1.elasticbeanstalk.com/")
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
	private OfferRepository orepo;
	@Autowired
	CarImageRepository cirepo;
	@Autowired
	CarPostRepository cprepo;

	@GetMapping("/getOne/{id}")
	public CarPosting getCar(@PathVariable("id") Integer id) {
		return cpservice.findCarPostById(id);
	}

	@DeleteMapping("/deletePost/{id}")
	@Transactional
	public ResponseEntity<HttpStatus> deleteCarPost(@PathVariable("id") Integer id) {
		CarPosting carpost = cpservice.findCarPostById(id);

		// get likers
		List<User> users = carpost.getUsers();

		// unmap and delete
		//unmap owners
		carpost.setOwner(null);
		if (carpost.getCarPostImage() != null)
			cirepo.delete(carpost.getCarPostImage());
		//unmap and deleteImage
		// CarImage img = carpost.getCarPostImage();
		// img.setCarpost(null);
		// cirepo.delete(img);

		//unmap offers
		
		
		cpservice.delete(carpost);


		// send out notification
		for (User user : users) {
			Notifications notification = new Notifications(carpost.getDescription() + " has been deleted!");
			notification.setUser(user);
			nservice.save(notification);
			user.getNotifications().add(notification);
			uservice.save(user);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Transactional
	@PostMapping("/savePost/{id}")
	public ResponseEntity<CarPosting> saveCarPost(@RequestBody CarPosting carpost, @PathVariable("id") Integer imgId) {

		// find the current logged in
		User user = uservice.finduserById(carpost.getUserId());

		// One hot decoding for category and brand
		String hotBrand = carpost.getBrand();
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

		String hotCategory = carpost.getCategory();
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
		// checkif carpost is new or old
		if (carpost.getPostId() == 0) {
			try {
				// Instatiate a new carpost from the object received from client side
				CarPosting newCarPosting = new CarPosting(carpost.getPrice(), carpost.getDescription(), realBrand,
						carpost.getEngineCapacity(), carpost.getRegisteredDate(), carpost.getMileage(), realCategory,
						carpost.getCarPostImage(), user);

				List<CarPosting> existingList = cpservice.findCarPostByUserId(carpost.getUserId());
				// set this post to the user if this dun have post
				if (existingList.size() == 0) {
					List<CarPosting> newpostList = new ArrayList<CarPosting>();
					newpostList.add(newCarPosting);
					user.setPostings(newpostList);
					uservice.save(user);

					// List<User> existingUserList = newCarPosting.getUsers();
					// existingUserList.add(user);
					// carpost.setOwner(user);
				} else {
					existingList.add(newCarPosting);
					// user.setHistory(existingList);
					uservice.save(user);
				}

				// set car image to the post
				CarImage img1 = cirepo.findByImageId(imgId);
				carpost.setCarPostImage(img1);


				CarPosting newcarPosting2 = cprepo.save(newCarPosting);


				img1.setCarpost(newCarPosting);
				cirepo.save(img1);

				// Notifitiona relevant ppl that new post created
				List<User> users = (ArrayList<User>) uservice.findAll();


				for (User userCheckNotification : users) {
					// if have notification and it is not the person doing the posting
					if (userCheckNotification.getPreference() != null
							&& userCheckNotification.getUserId() != user.getUserId()) {
						Preference preference = userCheckNotification.getPreference();

						// if the new post matches som1's preference
						if (preference.getBrand().equals(newCarPosting.getBrand()))
							if (preference.getCategory().equals(newCarPosting.getCategory()))
								if (preference.getEngineCapacityMax() >= newCarPosting.getEngineCapacity())
									if(preference.getEngineCapacityMin() <= newCarPosting.getEngineCapacity())
								 		if (preference.getHighestPrice() >= newCarPosting.getPrice())
										 	if(preference.getDepreciationMax()>=newCarPosting.getDepreciation()) {
													Notifications ntf = new Notifications("New Arrival", userCheckNotification,
													"A new arrival that matches your preference is  " + newCarPosting.getBrand());
													nservice.save(ntf);

													userCheckNotification.getNotifications().add(ntf);
													uservice.save(userCheckNotification);
								}
							}
						}
 

						return new ResponseEntity<>(newcarPosting2, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			}
		}
		
		// if post is old and wants to edit
		else {
			CarPosting oldCarpost = cpservice.findCarPostById(carpost.getPostId());
			CarPosting oldCarpostTemp = cpservice.findCarPostById(carpost.getPostId());
			double oldPrice=oldCarpost.getPrice();
			
			
			oldCarpost.setPrice(carpost.getPrice()); 
			oldCarpost.setDescription(carpost.getDescription());
			oldCarpost.setCategory(realCategory);
			oldCarpost.setEngineCapacity(carpost.getEngineCapacity());
			oldCarpost.setRegisteredDate(carpost.getRegisteredDate());
			oldCarpost.setMileage(carpost.getMileage());
			oldCarpost.setBrand(realBrand);

			// delete old image if there is one

			CarImage oldImage = oldCarpost.getCarPostImage();
			if (oldImage != null) {
				oldImage.setCarpost(null);
				cirepo.delete(oldImage);
			}

			// set new image
			CarImage newImg = cirepo.findByImageId(imgId);
			oldCarpost.setCarPostImage(newImg);
			cprepo.save(oldCarpost);

			newImg.setCarpost(oldCarpost);
			cirepo.save(newImg);
			
		double newPrice=oldCarpost.getPrice();
		if(((oldPrice-newPrice)/oldPrice)*100>=20){
				List<User> users=(ArrayList<User>) uservice.findAll();
				for (User user2 : users) {
					//check whether any user has this carpost as his favourite and check whether the user 
					//is not the one editing the price currently
					if(user2.getUserId()!=user.getUserId() && user2.getFavourites().contains(oldCarpostTemp)){
						Notifications ntf = new Notifications("Price Change", user2,
									"A new price change for your favourite carpost " + oldCarpost.getBrand() + "is $" + newPrice);
									nservice.save(ntf);
									user2.getNotifications().add(ntf);
									uservice.save(user2);
					}
					
				}
				
				
			
			}
		return new ResponseEntity<>(oldCarpost, HttpStatus.CREATED);	
		}
	}

	@PostMapping("/listPost")
	public List<CarPosting> listCarPost(@RequestBody SearchObject searchobject) {
		

		if(searchobject.getUserId() != 0){
		//sends list of recommended
			if(searchobject.getCriteria() == 1)
				return listCarPostByPref(searchobject.getUserId());
				
			//sends watch list
			else if (searchobject.getCriteria() == 2)
				return getWatchList(searchobject.getUserId());
		}
		

		//if not log in jus search
		String brand = searchobject.getBrand();
		String priceLabel = searchobject.getPrice();
		String description = searchobject.getDescription();

		// set brand
		if (searchobject.getBrand() == "")
			brand = null;

		// set description
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

	// @PostMapping("/listPostByPref/{id}")
	public List<CarPosting> listCarPostByPref(@PathVariable("id") int id) {
		
			//find user
			User userPref=uservice.finduserById(id);
			//display the cars matching with user preference
			List<CarPosting> prefCars=new ArrayList<CarPosting>();
			if(userPref.getPreference()!=null){
				Preference pref=userPref.getPreference();
				List<CarPosting> Cars=(ArrayList<CarPosting>) cpservice.findAll();
				
				for (CarPosting carPosting : Cars) {
					if(carPosting.getBrand().equals(pref.getBrand()) && carPosting.getCategory().equals(pref.getCategory())
					&& carPosting.getPrice()<=pref.getHighestPrice()){
						prefCars.add(carPosting);
					}
					
				}
				return prefCars;
			}
			else return null;

	}

	public List<CarPosting> getWatchList(@PathVariable("id") Integer id){
		User user = uservice.finduserById(id);
		return user.getFavourites();
	}


	@GetMapping("/hotcars") 
	public List<CarPosting> hotcars() { 
		List<CarPosting> mostViewed = filtertop3Cars(cpservice.findMostViewedCars()); 
		List<CarPosting> mostliked = filtertop3Cars(cpservice.findMostLikedCars());  

		List<CarPosting> NewList = new ArrayList<>();
		for (CarPosting likecar: mostliked){
			for(CarPosting viewcar: mostViewed){
				if(viewcar.equals(likecar)){
					NewList.add(likecar);
				}
			}
		}
		for (CarPosting likecar: mostViewed){
			if (!NewList.contains(likecar))
				NewList.add(likecar);
		}
		for (CarPosting viewcar: mostliked){
			if (!NewList.contains(viewcar))
				NewList.add(viewcar);
		}

		return NewList; 
	} 
	
	private List<CarPosting> filtertop3Cars(List<CarPosting> list){ 
		List<CarPosting> result =  new ArrayList<>(); 
		int top = 0; 
		for (CarPosting cp: list){ 
			if ( top < 3){ 
				result.add(cp); 
				top++; 
			} 
			else { 
				top = 0; 
				break; 
			} 
		} 
		return result; 
	} 
	

	@PostMapping("/saveOffer/{id}")
	public ResponseEntity<Offer> createOffer(@PathVariable("id") int postId, @RequestBody Offer offer) {
		try {
			// check if offer before
			CarPosting currentPost = cpservice.findCarPostById(postId);
			List<Offer> allCurrentOfferForCurrentPost = currentPost.getOffers();
			for (Offer oldOffer : allCurrentOfferForCurrentPost) {
				// if offer before
				if (oldOffer.getUser().getUserId() == offer.getUserId()) {
					oldOffer.setOffer(offer.getOffer());
					orepo.save(oldOffer);
					return new ResponseEntity<>(oldOffer, HttpStatus.CREATED);
				}
			}

			// if never offer before
			User user = uservice.finduserById(offer.getUserId());
			Offer newOffer = new Offer(offer.getOffer(), user, currentPost);
			orepo.save(newOffer);

			//let owner know got new offer for his car.
			User owner = currentPost.getOwner();
			List<Notifications> ownerNotification = owner.getNotifications();
			Notifications ntf = new Notifications("New Offer", owner,"you have a new offer for your" + currentPost.getDescription());
			nservice.save(ntf);
			ownerNotification.add(ntf);
			owner.setNotifications(ownerNotification);
			uservice.save(owner);

			return new ResponseEntity<>(newOffer, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/checkOwnOffer/{id}")
	public ResponseEntity<List<Offer>> checkOwnOffer(@PathVariable("id") int postId, @RequestBody Offer offer) {
		// check if got post before
		CarPosting currentPost = cpservice.findCarPostById(postId);
		List<Offer> allCurrentOfferForCurrentPost = currentPost.getOffers();

		if (offer.getUserId() != currentPost.getOwner().getUserId()) {

			for (Offer oldOffer : allCurrentOfferForCurrentPost) {
				// if offer before
				if (oldOffer.getUser().getUserId() == offer.getUserId()) {
					List<Offer> oldOfferListWithOne = new ArrayList<>();
					oldOfferListWithOne.add(oldOffer);
					return new ResponseEntity<>(oldOfferListWithOne, HttpStatus.CREATED);
				}
			}

			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			
			//need to create custom list because we are using @Jsonignore to solve infinite recursssion problem
			// the offers in this list should not have any user or carposting object as part of its attribute
			// should solve the problem of the data not being deserialized properly.
			//we need to display username, offeramount and email. 
			List<Offer> customOfferList = new ArrayList<>();
			for (Offer individualOffer: allCurrentOfferForCurrentPost){
				Offer offerX = new Offer(individualOffer.getOffer(), individualOffer.getUser().getUsername(), individualOffer.getUser().getEmail());
				customOfferList.add(offerX);
			}


			return new ResponseEntity<>(customOfferList, HttpStatus.ACCEPTED);
		}

	}

	@PostMapping("/saveImage")
	public ResponseEntity<Integer> saveImage(@RequestParam("photoParam") MultipartFile file) {

		CarImage currentCarImage = new CarImage();
		try {
			currentCarImage.setCarpostImage(file.getBytes());
			currentCarImage.setName(file.getName());
			currentCarImage.setType(file.getContentType());
		}

		catch (Exception e) {
			System.out.println(e);
		}
		;
		CarImage savedImage = cirepo.save(currentCarImage);
		return new ResponseEntity<>(savedImage.getImageId(), HttpStatus.CREATED);

	}

	@GetMapping("/getowncars/{id}")
	public List<CarPosting> getOwnCar(@PathVariable("id") Integer id) {
		User user = uservice.finduserById(id);
		return user.getPostings();
	}

}
