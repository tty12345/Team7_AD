package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.User;
import com.example.demo.repo.CarPostRepository;
import com.example.demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//pushagain
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
public class MainApplication {

	@Autowired
	CarPostRepository cpRepo;

	@Autowired
	UserRepository urepo;

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(MainApplication.class, args);
	}
	
		@Bean
		CommandLineRunner runner() {
			return args -> {
		
		User u1 = new User();
		urepo.save(u1);
				
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

		CarPosting post1 = new CarPosting(65000,"Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000, "Hatchback","https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg",u1);
		CarPosting post2 = new CarPosting(14998,"Edix 2.0A", "Honda", 1998, date2, 155387, "Hatchback","https://i.i-sgcm.com/cars_used/202107/1018213_small.jpg",u1);
		CarPosting post3 = new CarPosting(52515,"3 HB 1.5A Deluxe", "Mazda	", 1496, date3, 93000, "Hatchback", "https://i.i-sgcm.com/cars_used/202106/1001996_small.jpg",u1);
		CarPosting post4 = new CarPosting(240800,"911 Carrera Cabriolet 3.6A PDK", "Porsche", 3614, date4, 102000, "Sports","https://i.i-sgcm.com/cars_used/202107/1013874_small.jpg",u1);
		CarPosting post5 = new CarPosting(258800,"911 Carrera Cabriolet 3.6A PDK", "Porsche", 3614, date5, 81000, "Sports","https://i.i-sgcm.com/cars_used/202106/1009173_small.jpg",u1);
		CarPosting post6 = new CarPosting(93800, "Civic Type R 2.0M","Honda", 1998, date6, 50000, "Sports","https://i.i-sgcm.com/cars_used/202108/1021450_small.jpg",u1);
		CarPosting post7 = new CarPosting(99800, "C-Class C180 Avantgarde","Mercedes-Benz", 1595, date7, 60323, "Luxury","https://i.i-sgcm.com/cars_used/202106/1004393_small.jpg",u1);
		CarPosting post8 = new CarPosting(42000, "Camry 2.4A", "Toyota", 2362, date8, 100000, "Luxury","https://i.i-sgcm.com/cars_used/202108/1021441_small.jpg",u1);
		cpRepo.save(post1);
		cpRepo.save(post2);
		cpRepo.save(post3);
		cpRepo.save(post4);
		cpRepo.save(post5);
		cpRepo.save(post6);
		cpRepo.save(post7);
		cpRepo.save(post8);

		List<CarPosting> cpl1 = new ArrayList<CarPosting>();
		cpl1.add(post1);
		cpl1.add(post2);
		cpl1.add(post3);
		cpl1.add(post4);
		cpl1.add(post5);
		cpl1.add(post6);
		cpl1.add(post7);
		cpl1.add(post8);

		u1.setPostings(cpl1);
		urepo.save(u1);

		};
	}

}
