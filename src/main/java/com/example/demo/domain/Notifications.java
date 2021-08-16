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
    public int notificationId;
    private String type;
    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    @ManyToOne
    private User user;

    public Notifications() {
    }

    public Notifications(String type, User user, String msg) {
        this.type = type;
        this.user = user;
        this.Msg = msg;
    }

    public Notifications(int notificationId, String type, User user, String msg) {
        this.notificationId = notificationId;
        this.type = type;
        this.user = user;
        this.Msg = msg;
    }

<<<<<<< HEAD
=======
    




    public Notifications(String type) {
        this.type = type;
    }


    public Notifications(String type, User user) {
        this.type = type;
        this.user = user;
    }


>>>>>>> vidya
    public int getnotificationId() {
        return this.notificationId;
    }

    public void setnotificationId(int notificationId) {
        this.notificationId = notificationId;
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
