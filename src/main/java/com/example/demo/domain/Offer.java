package com.example.demo.domain;

import java.util.Objects;

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
    //ownerId
    private int userId;
    private String offererEmail;
    private String offererName;
   
    //owner object
    @ManyToOne
    private User user;

    @ManyToOne
    private CarPosting post;

    public Offer() {
        super();
    }

    public Offer(int offer, User user, CarPosting post) {
        super();
        this.offer = offer;
        this.user = user;
        this.post = post;
    }

    public Offer(int offer) {
        super();
        this.offer = offer;
    }
    
    public Offer(int offer, String offererName, String offererEmail){
        super();
        this.offer = offer;
        this.offererName = offererName;
        this.offererEmail =offererEmail;
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
        return post;
    }

    public void setPost(CarPosting post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Offer)) {
            return false;
        }
        Offer offerX = (Offer) o;
        return offerId == offerX.offerId && offer == offerX.offer && Objects.equals(user, offerX.user) && Objects.equals(post, offerX.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, offer, user, post);
    }


    @Override
    public String toString() {
        return "{" +
            " offerId='" + getOfferId() + "'" +
            ", offer='" + getOffer() + "'" +
            ", user='" + getUser() + "'" +
            ", post='" + getPost() + "'" +
            "}";
    }

    public int getUserId(){
        return userId;
    }

    public String getOffererEmail(){
        return offererEmail;
    }

    public String getOffererName(){
        return offererName;
    }

}
