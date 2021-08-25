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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
      property = "postId")
public class CarPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;
    private int price;
    private String description;
    private String brand;
    private int engineCapacity;
 
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date registeredDate;
    private int mileage;
    private String category;
    private String photoUrl;
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

    public CarPosting(CarImage carpostimage){
        super();
        this.carpostimage=carpostimage;
        
    }

    public CarPosting(int postId, int price, String description, String brand, int engineCapacity, Date registeredDate,
            int mileage, String category, String photoUrl, int views, List<User> users, User owner,
            List<User> history) {
        this.postId = postId;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.photoUrl = photoUrl;
        this.views = views;
        this.users = users;
        this.owner = owner;
        this.history = history;
    }

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage,
    String category, String photoUrl, User owner, int views, List<User> users, int likes) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.photoUrl = photoUrl;
        this.owner = owner;
        this.views = views;
        this.users = users;
        this.likeCount = likes;
    }

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage,
    String category, String photoUrl, User owner) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.photoUrl = photoUrl;
        this.owner = owner;
    }

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage,
    String category, String photoUrl, CarImage carpostimage, User owner) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.registeredDate = registeredDate;
        this.mileage = mileage;
        this.category = category;
        this.photoUrl = photoUrl;
        this.carpostimage = carpostimage;
        this.owner = owner;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public CarImage getCarpostimage() {
        return this.carpostimage;
    }

    public void setCarpostimage(CarImage carpostimage) {
        this.carpostimage = carpostimage;
    }

    @Override
    public String toString() {
        return "{" + " postId='" + getPostId() + "'" + ", price='" + getPrice() + "'" + ", description='"
                + getDescription() + "'" + ", brand='" + getBrand() + "'" + ", engineCapacity='" + getEngineCapacity()
                + "'" + ", reisgteredDate='" + getRegisteredDate() + "'" + ", mileage='" + getMileage() + "'"
                + ", category='" + getCategory() + "'" + ", photoUrl='" + getPhotoUrl() + "'" + ", views='" + getViews()
                + "'" + ", user='" + getUsers() + "'" + ", history='" + getHistory() + "'" + "}";
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CarPosting)) {
            return false;
        }
        CarPosting carPosting = (CarPosting) o;
        return postId == carPosting.postId && price == carPosting.price && Objects.equals(description, carPosting.description) && Objects.equals(brand, carPosting.brand) && engineCapacity == carPosting.engineCapacity && Objects.equals(registeredDate, carPosting.registeredDate) && mileage == carPosting.mileage && Objects.equals(category, carPosting.category) && Objects.equals(photoUrl, carPosting.photoUrl) && views == carPosting.views && userId == carPosting.userId && Objects.equals(carpostimage, carPosting.carpostimage) && Objects.equals(users, carPosting.users) && Objects.equals(owner, carPosting.owner) && Objects.equals(offers, carPosting.offers) && Objects.equals(history, carPosting.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, price, description, brand, engineCapacity, registeredDate, mileage, category, photoUrl, views, userId, carpostimage, users, owner, offers, history);
    }


}