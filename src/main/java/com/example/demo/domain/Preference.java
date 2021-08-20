package com.example.demo.domain;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Preference {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 private String model;
	 private String brand;
	 private int lowestPrice;
	 private int highestPrice;
	 private String category;
	 private int registeredYearMin;
	 private int registeredYearMax;
	 private int depreciationMin;
	 private int depreciationMax;
	 private int engineCapacityMin;
	 private int engineCapacityMax;

	 @OneToOne
	 private User user;

	public Preference() {
		super();
	}

	public Preference(int id, String model, String brand, int lowestPrice, int highestPrice, String category,
			int registeredYearMin, int registeredYearMax, int depreciationMin, int depreciationMax,
			int engineCapacityMin, int engineCapacityMax, User user) {
		super();
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
		this.category = category;
		this.registeredYearMin = registeredYearMin;
		this.registeredYearMax = registeredYearMax;
		this.depreciationMin = depreciationMin;
		this.depreciationMax = depreciationMax;
		this.engineCapacityMin = engineCapacityMin;
		this.engineCapacityMax = engineCapacityMax;
		this.user = user;
	}

	public Preference(String model, String brand, int lowestPrice, int highestPrice, String category,
			int registeredYearMin, int registeredYearMax, int depreciationMin, int depreciationMax,
			int engineCapacityMin, int engineCapacityMax, User user) {
		super();
		this.model = model;
		this.brand = brand;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
		this.category = category;
		this.registeredYearMin = registeredYearMin;
		this.registeredYearMax = registeredYearMax;
		this.depreciationMin = depreciationMin;
		this.depreciationMax = depreciationMax;
		this.engineCapacityMin = engineCapacityMin;
		this.engineCapacityMax = engineCapacityMax;
		this.user = user;
	}

	public Preference(String model, String brand, int lowestPrice, int highestPrice, String category,
			int registeredYearMin, int registeredYearMax, int depreciationMin, int depreciationMax,
			int engineCapacityMin, int engineCapacityMax) {
		super();
		this.model = model;
		this.brand = brand;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
		this.category = category;
		this.registeredYearMin = registeredYearMin;
		this.registeredYearMax = registeredYearMax;
		this.depreciationMin = depreciationMin;
		this.depreciationMax = depreciationMax;
		this.engineCapacityMin = engineCapacityMin;
		this.engineCapacityMax = engineCapacityMax;
	}

	

	
	public Preference(String model, String brand, int lowestPrice, int highestPrice, String category,
			int engineCapacityMin, int engineCapacityMax, User user) {
		this.model = model;
		this.brand = brand;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
		this.category = category;
		this.engineCapacityMin = engineCapacityMin;
		this.engineCapacityMax = engineCapacityMax;
		this.user = user;
	}

	public Preference(String model, String brand, User user) {
		super();
		this.model = model;
		this.brand = brand;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(int lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public int getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(int highestPrice) {
		this.highestPrice = highestPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRegisteredYearMin() {
		return registeredYearMin;
	}

	public void setRegisteredYearMin(int registeredYearMin) {
		this.registeredYearMin = registeredYearMin;
	}

	public int getRegisteredYearMax() {
		return registeredYearMax;
	}

	public void setRegisteredYearMax(int registeredYearMax) {
		this.registeredYearMax = registeredYearMax;
	}

	public int getDepreciationMin() {
		return depreciationMin;
	}

	public void setDepreciationMin(int depreciationMin) {
		this.depreciationMin = depreciationMin;
	}

	public int getDepreciationMax() {
		return depreciationMax;
	}

	public void setDepreciationMax(int depreciationMax) {
		this.depreciationMax = depreciationMax;
	}

	public int getEngineCapacityMin() {
		return engineCapacityMin;
	}

	public void setEngineCapacityMin(int engineCapacityMin) {
		this.engineCapacityMin = engineCapacityMin;
	}

	public int getEngineCapacityMax() {
		return engineCapacityMax;
	}

	public void setEngineCapacityMax(int engineCapacityMax) {
		this.engineCapacityMax = engineCapacityMax;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	
	


	
	

	
	 

}

