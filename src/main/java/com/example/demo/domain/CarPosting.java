package com.example.demo.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "postId")
public class CarPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;
    private int price;
    private int depreciation;
    private String description;
    private String brand;
    private int engineCapacity;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date registeredDate;
    private int age;
    private int mileage;
    private String category;
    private int views;
    private int userId;
    private int likeCount;

    @OneToOne(mappedBy = "carpost")
    private CarImage carpostimage;

    // likers
    @ManyToMany
    private List<User> users = new ArrayList<>();

    @ManyToOne
    // @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "post")
    private List<Offer> offers = new ArrayList<>();

    @ManyToMany
    private List<User> history = new ArrayList<>();

    public CarPosting() {
        super();
    }

    public CarPosting(int postId, int price, int depreciation, String description, String brand, int engineCapacity,
            Date registeredDate, int mileage, String category, int views, List<User> users, User owner,
            List<User> history) {
        this.postId = postId;
        this.price = price;
        this.depreciation = depreciation;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.views = views;
        this.users = users;
        this.owner = owner;
        this.history = history;
    }

    public CarPosting(int price, String brand, int engineCapacity, String category, User owner) {
        this.price = price;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.category = category;
        this.owner = owner;
    }

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage,
            String category, CarImage carpostimage, User owner, int views, List<User> users, int likes) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.carpostimage = carpostimage;
        this.owner = owner;
        this.views = views;
        this.users = users;
        this.likeCount = likes;
    }

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage,
            String category, User owner) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.owner = owner;
    }

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage,
            String category, CarImage carpostimage, User owner) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.carpostimage = carpostimage;
        this.owner = owner;
    }

    // for the price estimate machine learning model
    public CarPosting(int depreciation, String brand, int engineCapacity, int age, int mileage, String category) {
        this.depreciation = depreciation;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.age = age;
        this.mileage = mileage;
        this.category = category;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // @JsonIgnore
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getHistory() {
        return history;
    }

    public void setHistory(List<User> history) {
        this.history = history;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public CarImage getCarPostImage() {
        return carpostimage;
    }

    public void setCarPostImage(CarImage carpostimage) {
        this.carpostimage = carpostimage;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "{" + " postId='" + getPostId() + "'" + ", price='" + getPrice() + "'" + ", description='"
                + getDescription() + "'" + ", brand='" + getBrand() + "'" + ", engineCapacity='" + getEngineCapacity()
                + "'" + ", reisgteredDate='" + getRegisteredDate() + "'" + ", mileage='" + getMileage() + "'"
                + ", category='" + getCategory() + "'" + "'" + ", views='" + getViews() + "'" + ", user='" + getUsers()
                + "'" + ", history='" + getHistory() + "'" + "}";
    }

    public int getUserId() {
        return userId;
    }

}