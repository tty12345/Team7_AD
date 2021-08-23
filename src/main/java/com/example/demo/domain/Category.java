package com.example.demo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = MyJsonDeserializer.class)
public class Category {
    private int id;
    private String name;
    private int quota;
    private int premium;

    public Category() {
    }

    public Category(int id, String name, int quota, int premium) {
        this.id = id;
        this.name = name;
        this.quota = quota;
        this.premium = premium;
    }

    public Category(String name, int quota, int premium) {
        this.name = name;
        this.quota = quota;
        this.premium = premium;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuota() {
        return this.quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getPremium() {
        return this.premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }
}
