package com.example.demo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
      property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String username;
    private String password;
    private UserType role;
    // post owned
    @OneToMany(mappedBy = "owner")
    // @JsonManagedReference
    private List<CarPosting> postings;

    @ManyToMany(mappedBy = "history")
    // @JsonManagedReference
    private List<CarPosting> history;

    // post liked
    @ManyToMany(mappedBy = "users")
    // @JsonManagedReference
    private List<CarPosting> favourites;

    @OneToOne(mappedBy = "user")
    private Preference preference;

    @OneToMany(mappedBy = "user")
    // @JsonManagedReference
    public List<Notifications> notifications;

    @OneToMany(mappedBy = "user")
    // @JsonManagedReference
    private List<Offer> offers;

    public User(String username, String password, List<CarPosting> postings) {
        this.username = username;
        this.password = password;
        this.postings = postings;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public User(String username, Preference preference) {
        this.username = username;
        this.preference = preference;
    }
    




    

    public User(String username, String password, UserType role, List<CarPosting> postings, List<CarPosting> history,
			List<CarPosting> favourites, Preference preference, List<Notifications> notifications,
			List<Offer> offers) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.postings = postings;
		this.history = history;
		this.favourites = favourites;
		this.preference = preference;
		this.notifications = notifications;
		this.offers = offers;
	}

	public User(String username, String password, UserType role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User() {
    }

    public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @JsonIgnore
    public List<CarPosting> getPostings() {
        return postings;
    }

    public void setPostings(List<CarPosting> postings) {
        this.postings = postings;
    }
    @JsonIgnore
    public List<CarPosting> getHistory() {
        return history;
    }

    public void setHistory(List<CarPosting> history) {
        this.history = history;
    }

    @JsonIgnore
    public List<CarPosting> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<CarPosting> favourites) {
        this.favourites = favourites;
    }
    @JsonIgnore
    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }
    @JsonIgnore
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }


    public Preference getPreference() {
        return preference;
    }


    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    



}
