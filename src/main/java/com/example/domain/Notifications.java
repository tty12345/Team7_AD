package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carposting")
public class Notifications {

    @Id
    private Integer Id;
    private String type;

    @ManyToOne
    private User user;
    

    public Notifications() {
    }


    public Notifications(Integer Id, String type, User user) {
        this.Id = Id;
        this.type = type;
        this.user = user;
    }


    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Notifications Id(Integer Id) {
        setId(Id);
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
