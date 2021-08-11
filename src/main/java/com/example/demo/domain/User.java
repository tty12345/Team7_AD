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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "user")
    private List<CarPosting> postings;

    @OneToOne(mappedBy = "user")
    private History history;

    @OneToOne(mappedBy = "user")
    private Favourites favourite;
    
    @OneToOne(mappedBy = "user")
    private Preferences preference;

    @OneToMany(mappedBy = "user")
    private List<Notifications> notification;


    public User(int userId, String username, String password, List<CarPosting> postings) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.postings = postings;
    }


    public User() {
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User userId(int userId) {
        setUserId(userId);
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public List<CarPosting> getPostings() {
        return this.postings;
    }

    public void setPostings(List<CarPosting> postings) {
        this.postings = postings;
    }

    public User postings(List<CarPosting> postings) {
        setPostings(postings);
        return this;
    }

}
