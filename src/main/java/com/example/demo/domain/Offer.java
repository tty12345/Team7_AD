package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int offerId;
    private int offer;

    @ManyToOne
    private User user;

    @ManyToOne
    private CarPosting post;

    public Offer() {
    }


    public Offer(int offer, User user, CarPosting post) {
        this.offer = offer;
        this.user = user;
        this.post = post;
    }
    


    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public CarPosting getPost() {
        return this.post;
    }

    public void setPost(CarPosting post) {
        this.post = post;
    }

}
