package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notificationId;
    private String type;

    @ManyToOne
    private User user;
    

    public Notifications() {
    }


    public Notifications(int notificationId, String type, User user) {
        this.notificationId = notificationId;
        this.type = type;
        this.user = user;
    }


    public int getnotificationId() {
        return this.notificationId;
    }

    public void setnotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public Notifications Id(int Id) {
        setnotificationId(Id);
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Notifications type(String type) {
        setType(type);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notifications user(User user) {
        setUser(user);
        return this;
    }


}