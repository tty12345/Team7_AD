package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.CarPosting;
import com.example.demo.repo.CarPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarPostServiceImplementation implements CarPostService {

    @Autowired
    CarPostRepository cprepo;

    @Override
    public CarPosting findCarPostById(Integer id) {
        return cprepo.findCarPostById(id);
    }

    @Override
    public void delete(CarPosting carpost) {
        cprepo.delete(carpost);
    }

    @Override
    public void save(CarPosting carpost) {
        cprepo.save(carpost);
    }

    @Override
    public List<CarPosting>  findAll() {
        return cprepo.findAll();
    }

    @Override
    public List<CarPosting> filterAllIgnoreCase(String brand, int minPrice, int maxPrice, String description) {
        return cprepo.filterAllIgnoreCase(brand, minPrice, maxPrice, description);
    }

    @Override
    public List<CarPosting> findCarPostByPref(String model, String brand) {
        return cprepo.findCarPostByPref(model, brand);
    }

    @Override
    public List<CarPosting> findCarPostByUserId(int userId) {
        return cprepo.findCarPostByUserId(userId);
    }

    @Override 
    public List<CarPosting> findMostViewedCars() { 
    return cprepo.findmostViewedCars(2); 
    } 
 
    @Override 
    public List<CarPosting> findMostLikedCars() { 
        return cprepo.findmostLikedCars(2); 
    }

    
}
