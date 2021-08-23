package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Offer;

public interface OfferService {

    List<Offer> findOffersByCarPostId(Integer id);

    void save(Offer newOffer);
    
}
