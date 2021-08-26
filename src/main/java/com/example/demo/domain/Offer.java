package com.example.demo.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
      property = "offerId")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int offerId;
    private int offer;
    private int userId;
    
    @ManyToOne
    // @JsonBackReference
    private User user;

    @ManyToOne
    // @JsonBackReference
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
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
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


}
