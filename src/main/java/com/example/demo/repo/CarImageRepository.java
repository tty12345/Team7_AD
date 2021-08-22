package com.example.demo.repo;

import com.example.demo.domain.CarImage;
import com.example.demo.domain.Preference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarImageRepository extends JpaRepository<CarImage,Integer> {


}
