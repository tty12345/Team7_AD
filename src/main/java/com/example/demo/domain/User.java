package com.example.demo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String username;
    private String password;
    private UserType role;
    // post owned
    @OneToMany(mappedBy = "owner")
    private List<CarPosting> postings;

    @ManyToMany(mappedBy = "history")
    private List<CarPosting> history;

    // post liked
    @ManyToMany(mappedBy = "users")
    private List<CarPosting> favourites;

    @OneToOne(mappedBy = "user")
    private Preference preference;

    @OneToMany(mappedBy = "user")
    public List<Notifications> notifications;

    @OneToMany(mappedBy = "user")
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

    public List<CarPosting> getPostings() {
        return postings;
    }

    public void setPostings(List<CarPosting> postings) {
        this.postings = postings;
    }

    public List<CarPosting> getHistory() {
        return history;
    }

    public void setHistory(List<CarPosting> history) {
        this.history = history;
    }


    public List<CarPosting> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<CarPosting> favourites) {
        this.favourites = favourites;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }

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
