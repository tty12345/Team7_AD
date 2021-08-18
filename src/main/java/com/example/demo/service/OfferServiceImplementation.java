package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Offer;
import com.example.demo.repo.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImplementation implements OfferService{

    @Autowired
    OfferRepository orepo;

    @Override
    public List<Offer> findOffersByCarPostId(Integer id) {
        return orepo.findOffersByCarPostId(id);
    }

    @Override
    public void save(Offer newOffer) {
        orepo.save(newOffer);
        
    }
    
}
