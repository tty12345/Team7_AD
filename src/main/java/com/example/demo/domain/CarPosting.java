package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CarPosting {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    //likers
    @ManyToMany
    private List<User> users;

    @ManyToOne
    @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Offer> offers;

    @ManyToMany
    @JsonManagedReference
    private List<User> history;

    public CarPosting() {
        super();
    }

    public CarPosting(int postId, int price, String description, String brand, int engineCapacity, Date registeredDate,
            int mileage, String category, String photoUrl, int views, List<User> users, User owner, List<User> history) {
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

    public CarPosting(int price, String description, String brand, int engineCapacity, Date registeredDate, int mileage, String category, String photoUrl,
            User owner) {
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

	@Override
    public String toString() {
        return "{" +
            " postId='" + getPostId() + "'" +
            ", price='" + getPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", brand='" + getBrand() + "'" +
            ", engineCapacity='" + getEngineCapacity() + "'" +
            ", reisgteredDate='" + getRegisteredDate() + "'" +
            ", mileage='" + getMileage() + "'" +
            ", category='" + getCategory() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", views='" + getViews() + "'" +
            ", user='" + getUsers() + "'" +
            ", history='" + getHistory() + "'" +
            "}";
    }

}