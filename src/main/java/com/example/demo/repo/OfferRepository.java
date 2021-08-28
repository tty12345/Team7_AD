package com.example.demo.repo;

import java.util.List;

import com.example.demo.domain.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query("select o from Offer o where o.post.postId = :id")
    List<Offer> findOffersByCarPostId(@Param("id") int id);

    @Query("select o from Offer o where o.user.userId = :id")
    List<Offer> findOfferByBuyerId(@Param("id") int id);
    
}