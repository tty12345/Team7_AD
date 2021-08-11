package com.example.demo.repo;

import com.example.demo.domain.CarPosting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPostRepository extends JpaRepository<CarPosting, Integer> {
    
}
