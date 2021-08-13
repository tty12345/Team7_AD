package com.example.demo.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "user")
    private List<CarPosting> postings;

    @OneToOne(mappedBy = "user")
    private History history;

    @OneToOne(mappedBy = "user")
    private Favourites favourite;
    
    @OneToOne(mappedBy = "user")
    private Preference preference;

    @OneToMany(mappedBy = "user")
    private List<Notifications> notification;


    public User(String username, String password, List<CarPosting> postings) {
        this.username = username;
        this.password = password;
        this.postings = postings;
    }

    


    public User(int userId, String username, String password, List<CarPosting> postings, History history,
            Favourites favourite, Preference preference, List<Notifications> notification) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.postings = postings;
        this.history = history;
        this.favourite = favourite;
        this.preference = preference;
        this.notification = notification;
    }
    





    public User(String username, Preference preference) {
        this.username = username;
        this.preference = preference;
    }




    public User() {
    }

    public int getId() {
		return userId;
	}
	public void setId(int id) {
		this.userId = id;
	}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public List<CarPosting> getPostings() {
        return postings;
    }

    public void setPostings(List<CarPosting> postings) {
        this.postings = postings;
    }

    public User postings(List<CarPosting> postings) {
        setPostings(postings);
        return this;
    }


    public Preference getPreference() {
        return preference;
    }


    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    



}
