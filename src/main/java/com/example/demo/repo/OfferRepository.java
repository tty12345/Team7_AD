package com.example.demo.repo;

import com.example.demo.domain.Offer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

    
}