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
    
    //post owned
    @OneToMany(mappedBy = "owner")
    private List<CarPosting> postings;

    @ManyToMany(mappedBy = "history")
    private List<CarPosting> history;

    //post liked
    @ManyToMany(mappedBy = "users")
    private List<CarPosting> favourites;
    
    @OneToOne(mappedBy = "user")
    private Preferences preference;

    @OneToMany(mappedBy = "user")
    public List<Notifications> notifications;


    public User(String username, String password, List<CarPosting> postings) {
        this.username = username;
        this.password = password;
        this.postings = postings;
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

    public List<CarPosting> getHistory(){
        return history;
    }

    public void setHistory(List<CarPosting> history){
        this.history = history;
    }

}
