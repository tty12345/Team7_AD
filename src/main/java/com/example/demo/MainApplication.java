package com.example.demo;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			User u1 = new User("tin", Pass, UserType.USER);
			User u2 = new User("cherwah", Pass1, UserType.USER);
			User u3 = new User("mike", Pass2, UserType.USER);
			User u4 = new User("steve", Pass3, UserType.USER);
			urepo.save(u1);
			urepo.save(u2);
			urepo.save(u3);
			urepo.save(u4);

			List<User> gp1 = new ArrayList<>();
			gp1.add(u1);
			List<User> gp2 = new ArrayList<>();
			gp2.add(u1);
			gp2.add(u2);
			gp2.add(u3);
			List<User> gp3 = new ArrayList<>();
			gp3.add(u3);
			gp3.add(u4);

			Preference pref1 = new Preference("911 Carrera Cabriolet 3.6A PDK", "Porsche", u1);
			prepo.save(pref1);

			String sDate1 = "22/11/2018";
			String sDate2 = "01/05/2008";
			String sDate3 = "29/01/2016";
			String sDate4 = "17/08/2011";
			String sDate5 = "29/04/2019";
			String sDate6 = "05/08/2008";
			String sDate7 = "18/10/2016";
			String sDate8 = "27/05/2008";
			String sDate9 = "21/01/2017";
			String sDate10 = "19/12/2016";
			String sDate11 = "18/11/2015";
			String sDate12 = "06/10/2017";
			String sDate13 = "05/09/2017";
			String sDate14 = "08/08/2018";
			String sDate15 = "14/01/2016";
			String sDate16 = "15/11/2017";
			String sDate17 = "16/08/2017";
			String sDate18 = "30/12/2016";
			String sDate19 = "27/10/2016";
			String sDate20 = "13/05/2019";
			String sDate21 = "17/01/2008";
			String sDate22 = "02/04/2015";
			String sDate23 = "30/09/2008";
			String sDate24 = "15/04/2008";
			String sDate25 = "30/06/2015";
			String sDate26 = "10/09/2008";

			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
			Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
			Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate4);
			Date date5 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate5);
			Date date6 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate6);
			Date date7 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate7);
			Date date8 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate8);
			Date date9 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate9);
			Date date10 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate10);
			Date date11 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate11);
			Date date12 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate12);
			Date date13 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate13);
			Date date14 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate14);
			Date date15 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate15);
			Date date16 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate16);
			Date date17 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate17);
			Date date18 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate18);
			Date date19 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate19);
			Date date20 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate20);
			Date date21 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate21);
			Date date22 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate22);
			Date date23 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate23);
			Date date24 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate24);
			Date date25 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate25);
			Date date26 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate26);

			// Car 1
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Suzuki Swift.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img1 = new CarImage(imgByte, "suzuki swift", "jpg");
				cirepo.save(img1);

				CarPosting post1 = new CarPosting(65000, "Swift 1.0A Turbo GLX", "Suzuki", 998, date1, 25000,
						"Hatchback", img1, u1, 1, gp3, 2);
				cpRepo.save(post1);
				img1.setCarpost(post1);
				cirepo.save(img1);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 2
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Honda Edix.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "honda edix", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(14998, "Edix 2.0A", "Honda", 1998, date2, 155387, "Hatchback", img, u1,
						2, gp2, 2);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 3
			try {
				Path localPath = Paths.get("src/main/resources/static/images/mazda 3.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "mazda 3", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(52515, "3 HB 1.5A Deluxe", "Mazda", 1496, date3, 93000, "Hatchback",
						img, u1, 2, gp3, 2);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 4
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Porsche Carrera.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "porsche carrera", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(240800, "911 Carrera Cabriolet 3.6A PDK", "Porsche", 3614, date4,
						102000, "Sports", img, u1, 4, gp1, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 5
			try {
				Path localPath = Paths.get("src/main/resources/static/images/porsche macan.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "porsche macan", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(245800, "Macan 2.0A PDK", "Porsche", 1984, date5, 26000, "SUV", img,
						u1, 4, null, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 6
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Honda Civic.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "honda civic type r", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(93800, "Civic Type R 2.0M", "Honda", 1998, date6, 50000, "Sports", img,
						u1, 5, gp1, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 7
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Merc Avantgarde.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "merc c180", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(99800, "C-Class C180 Avantgarde", "Mercedes-Benz", 1595, date7, 60323,
						"Luxury", img, u1, 0, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 8
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Toyota Camry.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "toyota camry", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(42000, "Camry 2.4A", "Toyota", 2362, date8, 100000, "Luxury", img, u1,
						3, gp3, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 9
			try {
				Path localPath = Paths.get("src/main/resources/static/images/nissan x-trail.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "nissan x-trail", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(75515, "X Trail 2.0A Premium 7-Seater Sunroof", "Nissan", 1997, date9,
						77000, "SUV", img, u2, 5, gp3, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 10
			try {
				Path localPath = Paths.get("src/main/resources/static/images/nissan note.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "nissan note", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(49988, "Note 1.2A DIG-S", "Nissan", 1198, date10, 40500, "Hatchback",
						img, u2, 2, gp3, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 11
			try {
				Path localPath = Paths.get("src/main/resources/static/images/nissan qashqai.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "nissan qashqai", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(48888, "Qashqai 1.2A DIG-T Premium", "Nissan", 1197, date11, 113984,
						"SUV", img, u2, 2, gp1, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 12
			try {
				Path localPath = Paths.get("src/main/resources/static/images/audi a3.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "audi a3", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(82999, "A3 Sportback 1.0A TFSI S-tronic", "Audi", 999, date12, 46000,
						"Hatchback", img, u2, 4, gp1, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 13
			try {
				Path localPath = Paths.get("src/main/resources/static/images/honda hrv.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "honda hrv", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(70500, "HR-V 1.5A LX", "Honda", 1496, date13, 42500, "SUV", img, u3, 7,
						gp1, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 14
			try {
				Path localPath = Paths.get("src/main/resources/static/images/mitsubishi outlander.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "mitsubishi outlander", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(85388, "Outlander 2.0A", "Mitsubishi", 1998, date14, 83884, "SUV", img,
						u3, 2, gp1, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 15
			try {
				Path localPath = Paths.get("src/main/resources/static/images/mitsubishi outlander 2.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "mitsubishi outlander 2", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(85388, "Outlander 2.4A Sunroof", "Mitsubishi", 2360, date15, 103705,
						"SUV", img, u3, 6, gp2, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 16
			try {
				Path localPath = Paths.get("src/main/resources/static/images/audi q7.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "audi q7", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(85388, "Q7 2.0A TFSI Quattro Tip", "Audi", 1984, date16, 47000, "SUV",
						img, u4, 3, gp3, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 17
			try {
				Path localPath = Paths.get("src/main/resources/static/images/audi q2.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "audi q2", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(85388, "Q2 1.0A TFSI S-tronic", "Audi", 999, date17, 50000, "SUV", img,
						u4, 2, gp3, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 18
			try {
				Path localPath = Paths.get("src/main/resources/static/images/lexus is200t.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "lexus is200t", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(108999, "IS Turbo IS200t Executive", "Lexus", 1998, date18, 54000,
						"Lexury", img, u4, 5, gp3, 1);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 19
			try {
				Path localPath = Paths.get("src/main/resources/static/images/honda accord.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "honda accord", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(76800, "Accord 2.0A VTi-S", "Honda", 1997, date19, 69000, "Luxury",
						img, u2, 0, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 20
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Mitsubishi Attrage.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Mitsubishi Attrage", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(57800, "Attrage 1.2A", "Mitsubishi", 1198, date20, 54221, "Sedan", img,
						u2, 0, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 21
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Mitsubishi Lancer.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Mitsubishi Lancer", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(42000, "Lancer EX 1.5A GLS", "Mitsubishi", 1498, date21, 100221,
						"Sedan", img, u2, 0, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 22
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Honda City.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Honda City", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(39800, "City 1.5A SV (OPC)", "Honda", 1498, date22, 70000, "Sedan",
						img, u2, 0, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 23
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Nissan Latio.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Nissan Latio", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(41800, "Nissan Latio 1.5A", "Nissan", 1498, date23, 30000, "Sedan",
						img, u2, 3, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 24
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Nissan GTR.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Nissan GTR", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(268000, "GTR 3.8A Premium", "Nissan", 3799, date24, 59000, "Sports",
						img, u2, 10, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 25
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Nissan Slyphy.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Nissan Slyphy", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(39800, "Slyphy 1.6A Premium", "Nissan", 1598, date25, 70000, "Sedan",
						img, u2, 2, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// Car 26
			try {
				Path localPath = Paths.get("src/main/resources/static/images/Toyota Wish.jpg");
				byte[] imgByte = FileUtils.readFileToByteArray(new File(localPath.toAbsolutePath().toString()));
				CarImage img = new CarImage(imgByte, "Toyota Wish", "jpg");
				cirepo.save(img);
				CarPosting post = new CarPosting(16800, "Wish 1.8A X", "Toyota", 1794, date26, 150000, "MPV", img, u2,
						1, null, 0);
				cpRepo.save(post);
				img.setCarpost(post);
				cirepo.save(img);
			} catch (Exception e) {
				System.out.println(e);
			}

			// cpRepo.save(post1);
			// cpRepo.save(post2);
			// cpRepo.save(post3);
			// cpRepo.save(post4);
			// cpRepo.save(post5);
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
			// cpl1.add(post1);
			// cpl1.add(post2);
			// cpl1.add(post3);
			// cpl1.add(post4);
			// cpl1.add(post5);
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