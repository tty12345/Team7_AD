package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.CarPosting;

public interface CarPostService {
    
    public CarPosting findCarPostById(Integer id);
    public void delete(CarPosting carpost);
    public void save(CarPosting favourites);
    public List<CarPosting> findAll();
    public List<CarPosting> filterAllIgnoreCase(String brand, int minPrice, int maxPrice, String description);
    public List<CarPosting> findCarPostByPref(String model, String brand);
    public List<CarPosting> findCarPostByUserId(int userId);
    public List<CarPosting> findMostViewedCars();
    public List<CarPosting> findMostLikedCars();

}
