package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String preferenceId;
    private String Model;
    private String Brand;
    private Integer lowPrice;
    private Integer hightPrice;
    private String Category;

    @OneToOne
    private User user;


    public Preferences() {
    }

    public Preferences(String preferenceId, String Model, String Brand, Integer lowPrice, Integer hightPrice, String Category, User user) {
        this.preferenceId = preferenceId;
        this.Model = Model;
        this.Brand = Brand;
        this.lowPrice = lowPrice;
        this.hightPrice = hightPrice;
        this.Category = Category;
        this.user = user;
    }


    public String getModel() {
        return this.Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public Preferences Model(String Model) {
        setModel(Model);
        return this;
    }

    public String getBrand() {
        return this.Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public Preferences Brand(String Brand) {
        setBrand(Brand);
        return this;
    }

    public Integer getLowPrice() {
        return this.lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Preferences lowPrice(Integer lowPrice) {
        setLowPrice(lowPrice);
        return this;
    }

    public Integer getHightPrice() {
        return this.hightPrice;
    }

    public void setHightPrice(Integer hightPrice) {
        this.hightPrice = hightPrice;
    }

    public Preferences hightPrice(Integer hightPrice) {
        setHightPrice(hightPrice);
        return this;
    }

    public String getCategory() {
        return this.Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public Preferences Category(String Category) {
        setCategory(Category);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Preferences user(User user) {
        setUser(user);
        return this;
    }

}
