package com.example.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    private Integer userId;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "user")
    private CarPosting postings;

    @OneToOne(mappedBy = "user")
    private History history;

    @OneToOne(mappedBy = "user")
    private Favourites favourite;
    
    @OneToOne(mappedBy = "user")
    private Preferences preference;

    @OneToMany(mappedBy = "user")
    private Notifications notification;


    public User(int userId, String username, String password, CarPosting postings) {
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

    public CarPosting getPostings() {
        return this.postings;
    }

    public void setPostings(CarPosting postings) {
        this.postings = postings;
    }

    public User postings(CarPosting postings) {
        setPostings(postings);
        return this;
    }

}
