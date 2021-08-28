package com.example.demo.domain;

public class SearchObject {

    private String brand;
    private String description;
    private String price;
    private int criteria;
    private int userId;
    
    private SearchObject(String brand, String description, String price, int criteria, int userId){
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.criteria = criteria;
        this.userId = userId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCriteria() {
        return criteria;
    }

    public void setCriteria(int criteria) {
        this.criteria = criteria;
    }

    public int getUserId() {
        return userId;
    }
        
}
