package com.example.demo.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
      property = "imageId")
public class CarImage {
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int imageId;

    @Lob
    private byte[] carpostImage;

    private String name;

    private String type;

    @OneToOne
    private CarPosting carpost;

    public CarImage(){
        super();
    }

    public CarImage( int imageId, byte[] carpostImage, String name, String type, CarPosting carpost) {
        this.imageId = imageId;
        this.carpostImage = carpostImage;
        this.name = name;
        this.type = type;
        this.carpost = carpost;
    }

    public CarImage( byte[] carpostImage, String name, String type) {
        this.carpostImage = carpostImage;
        this.name = name;
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    public CarPosting getCarpost() {
        return carpost;
    }

    public void setCarpost(CarPosting carpost) {
        this.carpost = carpost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CarImage)) {
            return false;
        }
        CarImage carImage = (CarImage) o;
        return imageId == carImage.imageId && Objects.equals(carpost, carImage.carpost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, carpost);
    }

    @Override
    public String toString() {
        return "{" +
            " imageId='" + getImageId() + "'" +
            ", carpost='" + getCarpost() + "'" +
            "}";
    }

    public byte[] getCarpostImage() {
        return carpostImage;
    }

    public void setCarpostImage(byte[] carpostImage) {
        this.carpostImage = carpostImage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
