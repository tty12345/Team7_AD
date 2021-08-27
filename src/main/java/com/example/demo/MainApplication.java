package com.example.demo;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.domain.*;
import com.example.demo.repo.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableEurekaClient
public class MainApplication {

	@Autowired
	CarPostRepository cpRepo;

	@Autowired
	UserRepository urepo;

	@Autowired
	PreferenceRepository prepo;

	@Autowired
	OfferRepository orepo;

	@Autowired
	NotificationRepository nrepo;

	@Autowired
	CarImageRepository cirepo;

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(MainApplication.class, args);
		// comment1
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder(); 
			String Pass = sCryptPasswordEncoder.encode("tin"); 
			String Pass1 = sCryptPasswordEncoder.encode("cherwah"); 
			String Pass2 = sCryptPasswordEncoder.encode("mike"); 
			String Pass3 = sCryptPasswordEncoder.encode("steve"); 
			User u1 = new User("tin",Pass, UserType.USER, 99999999); 
			User u2 = new User("cherwah",Pass1, UserType.USER,88888888); 
			User u3 = new User("mike",Pass2, UserType.USER, 77777777); 
			User u4 = new User("steve",Pass3, UserType.USER, 66666666); 
			urepo.save(u1); 
			urepo.save(u2); 
			urepo.save(u3); 
			urepo.save(u4); 
		  
			// List<User> gp1 = new ArrayList<>(); 
			// gp1.add(u1); 
			// List<User> gp2 = new ArrayList<>(); 
			// gp2.add(u1); gp2.add(u2); gp2.add(u3); 
			// List<User> gp3 = new ArrayList<>(); 
			// gp3.add(u3); gp3.add(u4); 
		  
			Preference pref1 = new Preference("911 Carrera Cabriolet 3.6A PDK", "Porsche", u1); 
			prepo.save(pref1); 
		  
			String sDate1 = "22/11/2018"; 
			String sDate2 = "01/05/2008"; 
			String sDate3 = "29/01/2016"; 
			String sDate4 = "17/08/2011"; 
			String sDate5 = "14/07/2010"; 
			String sDate6 = "05/08/2008"; 
			String sDate7 = "18/10/2016"; 
			String sDate8 = "27/05/2008"; 
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2); 
			Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3); 
			Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate4); 
			Date date5 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate5); 
			Date date6 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate6); 
			Date date7 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate7); 
			Date date8 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate8); 
		  
			// byte[] imgByte = FileUtils.readFileToByteArray(new File("C:/Users/Teck Yi/Desktop/orochimon-739x1024.jpg")); 
			// CarImage img1 = new CarImage(); 
			// img1.setCarpostImage(imgByte); 
			// CarImage img2 =  cirepo.save(img1); 
		  
			// CarPosting post1 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback", 
			//   "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1, 1, gp3, 2); 
			// // post1.setCarPostImage(img1); 
			 
			// CarPosting post2 = new CarPosting(14998, "Edix 2.0A", "Honda", 1998, date2, 155387, "Hatchback", 
			//   "https://i.i-sgcm.com/cars_used/202107/1018213_small.jpg", u1, 2, gp2, 2); 
			// CarPosting post3 = new CarPosting(52515, "3 HB 1.5A Deluxe", "Mazda ", 1496, date3, 93000, "Hatchback", 
			//   "https://i.i-sgcm.com/cars_used/202106/1001996_small.jpg", u1, 2, gp3, 2); 
			// CarPosting post4 = new CarPosting(240800, "911 Carrera Cabriolet 3.6A PDK", "Porsche", 3614, date4, 102000, 
			//   "Sports", "https://i.i-sgcm.com/cars_used/202107/1013874_small.jpg", u1, 4, gp1, 1); 
			// CarPosting post5 = new CarPosting(258800, "911 Carrera Cabriolet 3.6A PDK", "Porsche", 3614, date5, 81000, 
			//   "Sports", "https://i.i-sgcm.com/cars_used/202106/1009173_small.jpg", u1, 1, gp1, 1); 
			// CarPosting post6 = new CarPosting(93800, "Civic Type R 2.0M", "Honda", 1998, date6, 50000, "Sports", 
			//   "https://i.i-sgcm.com/cars_used/202108/1021450_small.jpg", u1, 5, gp1, 1); 
			// CarPosting post7 = new CarPosting(99800, "C-Class C180 Avantgarde", "Mercedes-Benz", 1595, date7, 60323, 
			//   "Luxury", "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1, 0, null, 0); 
			// CarPosting post8 = new CarPosting(42000, "Camry 2.4A", "Toyota", 2362, date8, 100000, "Luxury", 
			//   "https://i.i-sgcm.com/cars_used/202108/1021441_small.jpg", u1, 3, gp3, 1);

			CarPosting post1 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback", 
			  "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1); 
			CarPosting post2 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback", 
			  "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1); 
						CarPosting post3 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback", 
			  "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1); 
						CarPosting post4 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback", 
			  "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1); 
						CarPosting post5 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback", 
			  "https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg", u1); 

			
			cpRepo.save(post1);
			cpRepo.save(post2);
			cpRepo.save(post3);
			cpRepo.save(post4);
			cpRepo.save(post5);
			// cpRepo.save(post6);
			// cpRepo.save(post7);
			// cpRepo.save(post8);
			// img2.setCarpost(post1);
			// cirepo.save(img2);


			Notifications ntf1 = new Notifications("welcome", "Welcome to the web!", u1);
			Notifications ntf2 = new Notifications("delete",
					"Your favorite product information has been deleted, please click here for details", u1);
			nrepo.save(ntf1);
			nrepo.save(ntf2);

			List<CarPosting> cpl1 = new ArrayList<CarPosting>();
			cpl1.add(post1);
			cpl1.add(post2);
			cpl1.add(post3);
			cpl1.add(post4);
			cpl1.add(post5);
			// cpl1.add(post6);
			// cpl1.add(post7);
			// cpl1.add(post8);

			u1.setPostings(cpl1);
			urepo.save(u1);

			List<Notifications> currentNTF = new ArrayList<>();
			currentNTF.add(ntf1);
			currentNTF.add(ntf2);
			u1.setNotifications(currentNTF);
			urepo.save(u1);

			nrepo.save(new Notifications("Hellow"));
			nrepo.save(new Notifications("We"));
			nrepo.save(new Notifications("Are"));
			nrepo.save(new Notifications("Noobs!"));
		};
	}

}