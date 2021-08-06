package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {

    @Id
    private Integer Id;

    @OneToOne
    private CarPosting post;

    @OneToOne
    private User user;


    public History() {
    }


    public History(Integer Id, CarPosting post, User user) {
        this.Id = Id;
        this.post = post;
        this.user = user;
    }


    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public History Id(Integer Id) {
        setId(Id);
        return this;
    }

    public CarPosting getPost() {
        return this.post;
    }

    public void setPost(CarPosting post) {
        this.post = post;
    }

    public History post(CarPosting post) {
        setPost(post);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public History user(User user) {
        setUser(user);
        return this;
    }


}
