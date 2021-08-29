package com.example.demo.repo;

import com.example.demo.domain.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage,Integer> {

    public CarImage findByImageId(int id);



}
