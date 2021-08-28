package com.example.demo.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String username;
    private String password;
    private UserType role;
    private int mobileNumber;


    @OneToMany(mappedBy = "owner")
    private List<CarPosting> postings;

    // @ManyToMany(mappedBy = "history")
    // private List<CarPosting> history;

    // post liked

    @ManyToMany(mappedBy = "users")
    private List<CarPosting> favourites;

    @OneToOne(mappedBy = "user")
    private Preference preference;

    @OneToMany(mappedBy = "user")
    public List<Notifications> notifications;


    @OneToMany(mappedBy = "user")
    private List<Offer> offers;

    private String email;

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
            List<CarPosting> favourites, Preference preference, List<Notifications> notifications, List<Offer> offers) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
        this.postings = postings;
        // this.history = history;
        this.favourites = favourites;
        this.preference = preference;
        this.notifications = notifications;
        this.offers = offers;
    }

    public User(String username, String password, UserType role, String email) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(String username, String password, UserType role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, UserType role, int mobile) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
        mobileNumber = mobile;
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

    public User userId(int userId) {
        setUserId(userId);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User role(UserType role) {
        setRole(role);
        return this;
    }

    public int getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public User mobileNumber(int mobileNumber) {
        setMobileNumber(mobileNumber);
        return this;
    }

    public User postings(List<CarPosting> postings) {
        setPostings(postings);
        return this;
    }

    // public User history(List<CarPosting> history) {
    //     setHistory(history);
    //     return this;
    // }

    public User favourites(List<CarPosting> favourites) {
        setFavourites(favourites);
        return this;
    }

    public User preference(Preference preference) {
        setPreference(preference);
        return this;
    }

    public User notifications(List<Notifications> notifications) {
        setNotifications(notifications);
        return this;
    }

    public User offers(List<Offer> offers) {
        setOffers(offers);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
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

    // @JsonIgnore
    // public List<CarPosting> getHistory() {
    //     return history;
    // }

    // public void setHistory(List<CarPosting> history) {
    //     this.history = history;
    // }

    @JsonIgnore
    public List<CarPosting> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<CarPosting> favourites) {
        this.favourites = favourites;
    }

    // @JsonIgnore
    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }

    // @JsonIgnore
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username)
                && Objects.equals(password, user.password) && Objects.equals(role, user.role)
                && Objects.equals(postings, user.postings)
                && Objects.equals(favourites, user.favourites) && Objects.equals(preference, user.preference)
                && Objects.equals(notifications, user.notifications) && Objects.equals(offers, user.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, role, postings, favourites, preference, notifications,
                offers);
    }

}
