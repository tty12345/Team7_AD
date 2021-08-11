package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private Date reisgteredDate;
    private int mileage;
    private String category;
    private String photoUrl;
    private int views; 

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "post")
    private History history;

    @OneToOne(mappedBy = "post")
    private Favourites favourite;


    public CarPosting() {
        super();
    }

    public CarPosting(int price,String description, String brand, int engineCapacity, Date reisgteredDate, int mileage, String category, String photoUrl) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.reisgteredDate = reisgteredDate;
        this.mileage = mileage;
        this.category = category;
        this.photoUrl = photoUrl;
    }

    public CarPosting(int price,String description, String brand, int engineCapacity, Date reisgteredDate, int mileage, String category, String photoUrl, User user) {
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.reisgteredDate = reisgteredDate;
        this.mileage = mileage;
        this.category = category;
        this.photoUrl = photoUrl;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarPosting user(User user) {
        setUser(user);
        return this;
    }



    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public CarPosting postId(int postId) {
        setPostId(postId);
        return this;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarPosting price(int price) {
        setPrice(price);
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CarPosting brand(String brand) {
        setBrand(brand);
        return this;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public CarPosting engineCapacity(int engineCapacity) {
        setEngineCapacity(engineCapacity);
        return this;
    }

    public Date getReisgteredDate() {
        return reisgteredDate;
    }

    public void setReisgteredDate(Date reisgteredDate) {
        this.reisgteredDate = reisgteredDate;
    }

    public CarPosting reisgteredDate(Date reisgteredDate) {
        setReisgteredDate(reisgteredDate);
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public CarPosting mileage(int mileage) {
        setMileage(mileage);
        return this;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CarPosting category(String category) {
        setCategory(category);
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public CarPosting photoUrl(String photoUrl) {
        setPhotoUrl(photoUrl);
        return this;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public CarPosting views(int views) {
        setViews(views);
        return this;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CarPosting description(String description) {
        setDescription(description);
        return this;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public CarPosting history(History history) {
        setHistory(history);
        return this;
    }

    public Favourites getFavourite() {
        return favourite;
    }

    public void setFavourite(Favourites favourite) {
        this.favourite = favourite;
    }

    public CarPosting favourite(Favourites favourite) {
        setFavourite(favourite);
        return this;
    }
    
    

    @Override
    public String toString() {
        return "{" +
            " postId='" + getPostId() + "'" +
            ", price='" + getPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", brand='" + getBrand() + "'" +
            ", engineCapacity='" + getEngineCapacity() + "'" +
            ", reisgteredDate='" + getReisgteredDate() + "'" +
            ", mileage='" + getMileage() + "'" +
            ", category='" + getCategory() + "'" +
            ", photoUrl='" + getPhotoUrl() + "'" +
            ", views='" + getViews() + "'" +
            ", user='" + getUser() + "'" +
            ", history='" + getHistory() + "'" +
            ", favourite='" + getFavourite() + "'" +
            "}";
    }

}