package com.example.demo.domain;

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
      property = "notificationId")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notificationId;
    private String type;
    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    @ManyToOne
    // @JsonBackReference
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

    

    public Notifications(String msg) {
        this.Msg = msg;
    }


    public Notifications(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public Notifications(String type, String msg, User user) {
        this.type = type;
        this.Msg = msg;
        this.user = user;
    }


    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
