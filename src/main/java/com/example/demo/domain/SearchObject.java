package com.example.demo.domain;

public class SearchObject {

    private String brand;
    private String description;
    private String price;
    
    private SearchObject(String brand, String description, String price){
        this.brand = brand;
        this.description = description;
        this.price = price;
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
    
    
}
