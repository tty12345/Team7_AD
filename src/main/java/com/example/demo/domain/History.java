// package com.example.demo.domain;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToOne;


// @Entity
// public class History {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int historyId;

//     @OneToOne
//     private CarPosting post;

//     @OneToOne
//     private User user;


//     public History() {
//     }


//     public History(int historyId, CarPosting post, User user) {
//         this.historyId = historyId;
//         this.post = post;
//         this.user = user;
//     }

//     public CarPosting getPost() {
//         return this.post;
//     }

//     public void setPost(CarPosting post) {
//         this.post = post;
//     }

//     public History post(CarPosting post) {
//         setPost(post);
//         return this;
//     }

//     public User getUser() {
//         return this.user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

//     public History user(User user) {
//         setUser(user);
//         return this;
//     }


// }
